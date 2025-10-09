# Spring Boot Backend

This directory contains the backend Java code and configuration for the Spring Boot application.

## Structure

```
backend/
├── pom.xml                          # Maven configuration
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── example/
│       │           └── springbootapp/
│       │               ├── SpringBootAppApplication.java  # Main application class
│       │               └── controller/
│       │                   └── HelloController.java       # REST API endpoints
│       └── resources/
│           └── application.properties                     # Application configuration
```

## Features

- **Spring Boot 3.2.0** with Java 17
- **REST API** endpoints
- **H2 Database** for development
- **CORS Configuration** for frontend integration
- **JPA/Hibernate** for data persistence

## API Endpoints

- `GET /` - Home endpoint
- `GET /api/hello` - Hello API endpoint

## Configuration

The application is configured in `application.properties`:
- Server port: 8080
- H2 Database: In-memory for development
- JPA settings for automatic schema generation

## Running the Application

To run the backend application:

```bash
cd backend
mvn spring-boot:run
```

The application will be available at `http://localhost:8080`

## Database

H2 console is available at `http://localhost:8080/h2-console` when running in development mode.
