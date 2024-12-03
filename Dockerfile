# Build the application
FROM maven:3.8-amazoncorretto-17 AS builder

WORKDIR /app

COPY . /app

RUN mvn clean install -DskipTests

# Deploy the application with required native libraries
FROM amazoncorretto:17

LABEL maintainer="Grupo 47"

WORKDIR /app

RUN yum update -y && yum install -y tomcat-native

COPY --from=builder /app/target/produto-service-0.0.1-SNAPSHOT.jar /app/produto-service-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "produto-service-0.0.1-SNAPSHOT.jar"]

EXPOSE 8080