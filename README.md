# Reference Application Catalog

### Prerequisutes

* RabbitMQ 3.x
* Maven 3.x
* Java 8.x
* Docker (Optional, only if you want to use docker)
* Docker Compose (Optional, only if you want to use docker)

### Build locally (compile, test, package)

1. Start rabbitmq with default port  
2. Run the command `mvn clean pacakge`

### Build and create docker images 

1. Start rabbitmq with default port
2. Run the command `mvn clean pacakge fabric8:build`

### Build & generate helm charts

1. Start rabbitmq with default port
2. Run the command `mvn clean pacakge fabric8:build fabric:resource fabric8:helm`

### Run locally

1. Build the project (ref `Build locally` section above)
2. Start rabbitmq with default port
3. Run `java -jar reference-domain-service/target/reference-domain-service-0.0.1-SNAPSHOT.jar` to run the domain service
4. Run `java -jar reference-query-service/target/reference-query-service-0.0.1-SNAPSHOT.jar` to run query service


### Run locally in docker containers

1. Build the project (ref `Build and create docker images ` section above)
2. Run `docker-compose up`