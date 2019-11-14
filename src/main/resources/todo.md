# Configure TLS on a server
- generate a key pair [keytool guide](https://docs.oracle.com/javase/8/docs/technotes/tools/unix/keytool.html#keytool_option_genkeypair)
- create a CSR for trustme.com
- verify and sign the CSR using RootCA's private key stored in ca_root.jks
- import certificates to keystore/truststore
- change Spring SSL configuration to use the trustme.com certificate [spring guide](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#howto-configure-ssl)