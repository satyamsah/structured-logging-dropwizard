## intro
simple dropwizard application with OCI structured and normal logging

### build
mvn clean install
mvn package

### run
java -jar target/original-StructuredLogging-1.0-SNAPSHOT.jar  server src/main/resources/introduction-config.yaml 

### test

http://localhost:8080/hello 
