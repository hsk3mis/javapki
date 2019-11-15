# Again Fix app.javapki.DefaultSSLSocketTest.shouldHandshakeWithLocalhost
- put keystore into SSLContext (client)
- configure `server.ssl.trust-store*` (server)

Sample code using keystores and truststores can be found in [the official documentation](https://docs.oracle.com/javase/10/security/sample-code-illustrating-secure-socket-connection-client-and-server.htm)    