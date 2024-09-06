FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/mail-sender-moto-manager-0.0.1-SNAPSHOT.jar /app/mail-sender-moto-manager.jar
EXPOSE 8081

ENTRYPOINT ["java", "-jar", "mail-sender-moto-manager.jar"]
