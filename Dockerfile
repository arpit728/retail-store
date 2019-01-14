FROM openjdk:8u151-jdk-alpine
WORKDIR /opt/retail-store
COPY ["target/retail-store-*.jar", "app.jar"]
COPY config config
RUN chmod 777 config/startup.sh
EXPOSE 8080
EXPOSE 8081
ENTRYPOINT ["config/startup.sh"]