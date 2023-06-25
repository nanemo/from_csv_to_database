# Use an official OpenJDK 17 runtime as the base image
FROM adoptopenjdk:17-jdk-hotspot

# Set the working directory inside the container
WORKDIR /app

# Copy the packaged JAR file into the container at the working directory
COPY target/your-application.jar /app/your-application.jar

# Expose the port that your Spring Boot application listens on
EXPOSE 8080

# Define the command to run your application when the container starts
CMD ["java", "-jar", "your-application.jar"]