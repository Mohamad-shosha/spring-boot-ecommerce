FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/spring-boot-ecommerce-0.0.1-SNAPSHOT.jar spring-boot-ecommerce.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "spring-boot-ecommerce.jar"]