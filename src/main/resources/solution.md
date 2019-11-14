Create private key and self-signed certificate and place it in resources
```
$JAVA_HOME/bin/keytool -genkeypair -dname "cn=trustme.com" -keyalg RSA -keystore trustmekey.jks
```

Generate a Certificate Sign Request for public key stored in keystore
```
$JAVA_HOME/bin/keytool -certreq -keystore trustmekey.jks -file trustme.req
```

Sign the CSR in the name of CA
```
$JAVA_HOME/bin/keytool -gencert -infile trustme.req -keystore ca_root.jks -outfile trustme.cer
```

Export CA certificate from CA's keystore
```
$JAVA_HOME/bin/keytool -exportcert -keystore ca_root.jks -file ca_root.cer
```

Import CA certificate to trustme keystore
```
$JAVA_HOME/bin/keytool -importcert -alias caroot -file ca_root.cer -keystore trustmekey.jks
$JAVA_HOME/bin/keytool -importcert -alias mykey -file trustme.cer -keystore trustmekey.jks
```

Change Spring configuration key
```
server.ssl.key-store=classpath:trustmekey.jks
```

[Check it](https://localhost:8443)