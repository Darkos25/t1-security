# T1 Security API

The T1 Security project provides an API for user authentication and management using Spring Boot, Spring Security, and JWT.

## Overview

This is a basic web application for user authentication and authorization. It supports user registration, authentication with JWT tokens, and role-based access control.

## Features

- User registration
- Authentication using JWT tokens
- Role-based authorization
- PostgreSQL database integration
- API documentation with Swagger UI

## Tech Stack

- **Java 17**
- **Spring Boot**
- **Spring Security**
- **JWT (JSON Web Token)**
- **PostgreSQL**
- **Swagger (Springdoc OpenAPI)**

## Installation and Setup

### Requirements

- **JDK 17**
- **Maven 3.x**
- **PostgreSQL**

### Installation Steps

1. **Clone the repository:**

    ```bash
    git clone https://github.com/Darkos25/t1-security
    cd t1-security
    ```

2. **Database setup:**

   Create a PostgreSQL database and configure the connection properties in the `application.properties` or `application.yml` file:

    ```properties
    spring.application.name=T1-security
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    spring.jpa.hibernate.ddl-auto=update
    ```

3. **Build and run the application:**

   Use Maven to build and run the application:

    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

4. **Access the API and Swagger UI:**

   The application will be available at:

    ```
    http://localhost:8080
    ```

   Swagger UI documentation will be available at:

    ```
    http://localhost:8080/swagger-ui/index.html
    ```

## API Documentation

Swagger UI provides interactive documentation for your API. Here you can view and test all available endpoints.

- **Login (POST /api/v1/public/auth/login)** - Authenticate a user and obtain a JWT token.
- **Register (POST /api/v1/public/auth/register)** - Register a new user.

## Testing

To run tests, use the following command:

```bash
mvn test
```

## License
### Description:

- **Dependencies and Configuration**: The installation section provides instructions on cloning the repository, setting up the database, and building the application.
- **Swagger UI**: Mentions how to access the Swagger interface for API documentation.
- **Testing**: Describes commands to run tests.
- **Contact and License**: Provides contact information and license details.

This template can be adapted to your project's specific details, including repository links and contact information.
