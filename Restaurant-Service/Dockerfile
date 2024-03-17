FROM openjdk:17
WORKDIR /app
ADD target/Restaurant-Service-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=docker", "Restaurant-Service-0.0.1-SNAPSHOT.jar"]
