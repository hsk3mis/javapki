package app.javapki;

import javax.net.ssl.HostnameVerifier;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class HttpUtils {

    public static void httpGet(Socket socket) throws IOException {
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));

        out.println("GET / HTTP/1.1");
        out.println();
        out.flush();

        if (out.checkError()) {
            System.out.println("SSLSocketClient:  java.io.PrintWriter error");
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
        }

        in.close();
        out.close();
    }

    public static void successOnLocalhostHostnameVerification() {
        final HostnameVerifier defaultHostnameVerifier = javax.net.ssl.HttpsURLConnection.getDefaultHostnameVerifier ();
        final HostnameVerifier localhostAcceptedHostnameVerifier = (hostname, sslSession) -> {
            if ( hostname.equals ( "localhost" ) ) {
                return true;
            }
            return defaultHostnameVerifier.verify ( hostname, sslSession );
        };
        javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier ( localhostAcceptedHostnameVerifier );
    }

}
