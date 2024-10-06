# Stage 1: Build
FROM maven:3.8.6-openjdk-17-slim AS builder

# Set the working directory
WORKDIR /app
# Copy the Maven Wrapper scripts and the pom.xml
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Install dependencies using the Maven Wrapper
RUN chmod +x mvnw && ./mvnw dependency:go-offline

# Copy the source code
COPY src ./src

# Run tests and build the application using the Maven Wrapper
RUN ./mvnw clean package -DskipTests=false && echo "Tests and build the application ran successfully"

# Stage 2: Run
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the jar file from the target directory
ARG VERSION=0.0.1-SNAPSHOT
COPY target/care-track-${VERSION}.jar app.jar

# Expose the application port
EXPOSE 8081

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]