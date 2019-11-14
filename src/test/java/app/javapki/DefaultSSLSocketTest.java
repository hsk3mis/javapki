package app.javapki;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

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

            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket socket = (SSLSocket) factory.createSocket("localhost", localServerPort);

            socket.startHandshake();

            httpGet(socket);
            socket.close();
        });
    }


}
