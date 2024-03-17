FROM openjdk:21
WORKDIR /app
ADD target/Logger-Service-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=docker", "Logger-Service-0.0.1-SNAPSHOT.jar"]
