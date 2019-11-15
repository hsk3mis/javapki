# Put truststore into SSLContext
- Generate truststore with two trusted certs: ca_root.cer and trustme.cer
- Dont't use default SSLSocketFactory. Create SSLSocketFactory using SSLContext. But first initialize SSLContext with the truststore.

Sample code using keystores and truststores can be found in [the official documentation](https://docs.oracle.com/javase/10/security/sample-code-illustrating-secure-socket-connection-client-and-server.htm)    