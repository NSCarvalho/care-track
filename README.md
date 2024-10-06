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

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
