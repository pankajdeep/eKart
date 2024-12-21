# Use an official OpenJDK runtime as the base image
FROM openjdk:17-jdk-slim

# Set a working directory
WORKDIR /app

# Copy the JAR file of your Spring Boot app to the container
COPY target/product-service-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your application runs on
EXPOSE 8080

# Define the command to run your application
ENTRYPOINT ["java", "-jar", "app.jar"]