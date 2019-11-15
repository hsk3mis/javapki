Import all server's untrusted certificates to new client's keystore

```
$JAVA_HOME/bin/keytool -importcert -alias caroot -file ca_root.cer -keystore client_truststore.jks
$JAVA_HOME/bin/keytool -importcert -alias trustmeserver -file trustme.cer -keystore client_truststore.jks
```

Create SSL socket like the boss
```
SSLContext ctx = SSLContext.getInstance("TLS");
TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
KeyStore ks = KeyStore.getInstance("JKS");

InputStream clientTrustStoreInputStream = DefaultSSLSocketTest.class.getResourceAsStream("/client_truststore.jks");
ks.load(clientTrustStoreInputStream, "changeit".toCharArray());

tmf.init(ks);
ctx.init(null, tmf.getTrustManagers(), null);

SSLSocketFactory factory = ctx.getSocketFactory();
SSLSocket socket = (SSLSocket) factory.createSocket("localhost", localServerPort);
```

[Check it](https://localhost:8443)