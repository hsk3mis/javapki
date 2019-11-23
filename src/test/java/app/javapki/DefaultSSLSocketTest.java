package app.javapki;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import java.io.InputStream;
import java.security.KeyStore;

import static app.javapki.HttpUtils.httpGet;
import static app.javapki.HttpUtils.successOnLocalhostHostnameVerification;

public class DefaultSSLSocketTest extends BaseIntegrationTest {

    //src https://docs.oracle.com/javase/10/security/sample-code-illustrating-secure-socket-connection-client-and-server.htm
    @Test
    void shouldConnectToVerisignComUsingDefaultSSLSocketFactory() throws Exception{
        SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket socket = (SSLSocket) factory.createSocket("www.verisign.com", 443);

        socket.startHandshake();

        httpGet(socket);
        socket.close();
    }

    @Test
    void shouldNotHandshakeWithLocalhost(){
        Assertions.assertThrows(SSLHandshakeException.class, () -> {
            successOnLocalhostHostnameVerification();

            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket socket = (SSLSocket) factory.createSocket("localhost", localServerPort);

            socket.startHandshake();

            httpGet(socket);
            socket.close();
        });
    }

    @Test
    void shouldHandshakeWithLocalhost(){
        Assertions.assertDoesNotThrow(() -> {
            successOnLocalhostHostnameVerification();

            SSLContext ctx = SSLContext.getInstance("TLS");
            TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
            KeyStore ks = KeyStore.getInstance("JKS");

            InputStream clientTrustStoreInputStream = DefaultSSLSocketTest.class.getResourceAsStream("/client_truststore.jks");
            ks.load(clientTrustStoreInputStream, "qwerty".toCharArray());

            tmf.init(ks);
            ctx.init(null, tmf.getTrustManagers(), null);

            SSLSocketFactory factory = ctx.getSocketFactory();
            SSLSocket socket = (SSLSocket) factory.createSocket("localhost", localServerPort);

            socket.startHandshake();

            httpGet(socket);
            socket.close();
        });
    }


}
