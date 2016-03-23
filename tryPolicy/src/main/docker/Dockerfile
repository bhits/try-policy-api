FROM java:8
VOLUME /java
ADD try-policy-1.3.0-SNAPSHOT.jar /tryPolicy.jar
ENTRYPOINT ["java","-Djavax.net.ssl.trustStore=/java/keystore/mhc.keystore","-Djavax.net.ssl.trustStorePassword=changeit","-jar","/tryPolicy.jar"]