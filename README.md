# RetailStore

How to start the RetailStore application
---

1. Run `mvn clean install` to build your application
1. In your mysql create database with name `retail_store`
1. Then run `java -jar target/retail-store-1.0-SNAPSHOT.jar db migrate config/config.yml` to create all the tables
1. Start application with `java -jar target/retail-store-1.0-SNAPSHOT.jar server config/config.yml`

Health Check
---
To see your applications health enter url `http://localhost:8081/healthcheck`
