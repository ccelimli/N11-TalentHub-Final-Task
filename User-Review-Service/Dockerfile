FROM openjdk:21
WORKDIR /app
ADD target/User-Review-Service-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=docker", "User-Review-Service-0.0.1-SNAPSHOT.jar"]