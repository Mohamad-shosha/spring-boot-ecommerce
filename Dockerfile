FROM maven:3.8.5-openjdk-17 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM amazoncorretto:17-alpine

WORKDIR /app

COPY --from=build /app/target/spring-boot-ecommerce-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
