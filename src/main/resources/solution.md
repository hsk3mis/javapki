Create private key and self-signed certificate and place it in resources
```
$JAVA_HOME/bin/keytool -genkeypair -keyalg RSA -dname "CN=RootCA" -keystore ca_root.jks
```

Add Spring configuration 
```
server.ssl.key-store-type=JKS
server.ssl.key-store=classpath:ca_root.jks
server.ssl.key-store-password=changeit
server.ssl.key-alias=mykey

# optional
server.port=8443
```

[Check it](https://localhost:8443)