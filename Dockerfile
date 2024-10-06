# Stage 1: Build
FROM maven:3.8.4-openjdk-17-slim AS builder

# Set the working directory
WORKDIR /app

# Copy the Maven Wrapper scripts and the pom.xml
COPY pom.xml .
COPY mvnw .
COPY .mvn ./.mvn
COPY src ./src.

# Ensure the Maven Wrapper is executable
RUN chmod +x mvnw

# Download dependencies and build the project
RUN ./mvnw clean package

# Stage 2: Run
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the jar file from the target directory
ARG VERSION=0.0.1-SNAPSHOT
COPY --from=0  /app/target/care-track-${VERSION}.jar app.jar

# Expose the application port
EXPOSE 8081

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]