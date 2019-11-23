# Configure SSL on a server
- generate self-signed certificate [keytool guide](https://docs.oracle.com/javase/8/docs/technotes/tools/unix/keytool.html#keytool_option_genkeypair)
- add Spring SSL configuration [spring guide](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#howto-configure-ssl)


keytool -genkeypair -keyalg RSA -dname "CN=RootCA" -keystore ca_root.jks