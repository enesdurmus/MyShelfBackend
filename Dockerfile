FROM openjdk:21
WORKDIR /app
COPY target/my-shelf-backend-0.0.1-SNAPSHOT.jar /app
EXPOSE 8080
CMD ["java", "-jar", "my-shelf-backend-0.0.1-SNAPSHOT.jar"]