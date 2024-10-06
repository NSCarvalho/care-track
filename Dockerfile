# Use an official OpenJDK image as a base
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the Maven/Gradle wrapper and configuration files
COPY mvnw* ./
COPY .mvn .mvn

# Copy the application source code
COPY src ./src
COPY pom.xml ./pom.xml

# Make sure the mvnw script is executable
RUN chmod +x ./mvnw

# Build the application
RUN ./mvnw package -DskipTests=false

# Expose the port the application runs on
EXPOSE 8081

# Define the command to run the application
CMD ["java", "-jar", "target/care-track-0.0.1-SNAPSHOT.jar"]