# CareTrack

## Overview

**Care Track** is a demo project designed for tracking patient care and appointments. This application provides for tracking patient care and appointments, conveying an organized system for managing healthcare services.

## Features

- Track patient care and appointments.
- User-friendly interface for managing patient data.
- H2 in-memory database for easy testing and setup.
- H2 console enabled for database access during development.

## Prerequisites

- Java 17
- Docker (for Docker installation)
- Maven (for Spring Boot installation)

## Installation

### Using Docker

To run the application using Docker, follow these steps:

1. **Clone the repository:**

   ```bash
   git clone https://github.com/NSCarvalho/care-track.git
   cd care-track
   ```

2. **Build and run the Docker container:**

   Make sure you have Docker installed and running on your machine. Use the following command to start the application:

   ```bash
   docker-compose up --build
   ```

3. **Access the application:**

   Open your browser and go to `http://localhost:8081` to access the Care Track application.

### Using Spring Boot

To run the application locally without Docker, follow these steps:

1. **Clone the repository:**

   ```bash
   git clone https://github.com/NSCarvalho/care-track.git
   cd care-track
   ```

2. **Navigate to the project directory and run:**

   If you have Maven installed, you can run the application using:

   ```bash
   ./mvnw spring-boot:run
   ```

3. **Access the application:**

   Open your browser and go to `http://localhost:8081` to access the Care Track application.

## Environment Variables

The following environment variables are configured in the `docker-compose.yaml` file:

- **SPRING_DATASOURCE_URL:** `jdbc:h2:mem:testdb`
- **SPRING_DATASOURCE_DRIVER_CLASS_NAME:** `org.h2.Driver`
- **SPRING_DATASOURCE_USERNAME:** `sa`
- **SPRING_DATASOURCE_PASSWORD:** `password`
- **SPRING_H2_CONSOLE_ENABLED:** `true`

## Testing Services

A Postman collection is included in the `docs` folder to test the services. You can import the collection into Postman to start testing the API endpoints.

## FAQ

### 1. **Problem: `./mvnw: not found` Error during Docker Build**

When building the Docker image, you might encounter the following error:

```
ERROR [caretrack 8/8] RUN ./mvnw package -DskipTests=false
0.7s
------
 > [caretrack 8/8] RUN ./mvnw package -DskipTests=false:
0.649 /bin/sh: 1: ./mvnw: not found
```

#### Cause:
This issue usually occurs when the `mvnw` file does not have the correct line endings, especially if it was originally created on a Windows system. By default, Windows uses CRLF (`\r\n`) line endings, while Unix-based systems (like Linux, which Docker containers use) expect LF (`\n`) line endings.

#### Solution:
To resolve this issue, you need to:

1. **Ensure the `mvnw` file has Unix-style (LF) line endings:**
   - If you are working in an IDE, such as IntelliJ or VS Code, ensure that the `mvnw` file uses **LF** as the end-of-line (EOL) format.
   - You can also use the `dos2unix` command to convert the file:
     ```bash
     dos2unix mvnw
     ```

#### Steps to Fix:

1. Open a terminal and navigate to the directory containing the `mvnw` file.

2. Run the following command:
   ```bash
   # Convert the file to Unix EOL format
   dos2unix mvnw
   ```

3. Rebuild the Docker image:
   ```bash
   docker-compose up --build
   ```

After applying these changes, the Docker build should complete successfully without encountering the `mvnw: not found` error.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
