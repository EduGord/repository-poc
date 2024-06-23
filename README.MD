# Jdbc vs Jpa vs DAO Repositories tests

## Overview

This project is a sample application demonstrating the usage of different types of repositories for data access in a Spring Boot application. We are using Testcontainers for integration testing to ensure our application works correctly with actual databases in isolated environments.

## Getting Started

### Prerequisites

- Java 21
- Maven 3.3.2
- Docker (for running Testcontainers and Docker Compose)

### Installation

1. Navigate to the project directory:
   ```bash
   cd your-repo
   ```

## Testing

### Normal Tests with the "test" Profile

This project uses Testcontainers for integration tests. Testcontainers allows us to run tests in a Docker containerized environment, providing a more realistic testing scenario.

To run all tests using Testcontainers, use the following command:
```bash
mvn test
```

### Integration Tests with the "integration" Profile

To run integration tests with an external PostgreSQL database and Prometheus monitoring, follow these steps:

1. Build the Docker images and start the containers using Docker Compose:
   ```bash
   mvn clean package
   docker-compose up --build
   ```

2. Run the integration tests:
   ```bash
   mvn test -Dspring.profiles.active=integration
   ```

### Tests Overview

We have three main types of repository tests in this project:

1. **JdbcUserRepositoryTests**:
   - **Purpose**: To test the `JdbcUserRepository` which uses raw JDBC for data access.
   - **Description**: These tests ensure that the repository methods work correctly with the database using raw SQL queries.
   - **Testcontainers**: Uses a PostgreSQL container to run the tests against a real PostgreSQL database.
   - **Integration Tests**: Runs against an external PostgreSQL database.

2. **JpaUserRepositoryTests**:
   - **Purpose**: To test the `JpaUserRepository` which uses Spring Data JPA for data access.
   - **Description**: These tests ensure that the repository methods work correctly using JPA and Hibernate.
   - **Testcontainers**: Uses a PostgreSQL container to run the tests against a real PostgreSQL database.
   - **Integration Tests**: Runs against an external PostgreSQL database.

3. **UserDaoRepositoryTests**:
   - **Purpose**: To test the `UserDaoRepository` which uses a custom DAO (Data Access Object) pattern.
   - **Description**: These tests ensure that the custom DAO methods work correctly with the database.
   - **Testcontainers**: Uses a PostgreSQL container to run the tests against a real PostgreSQL database.
   - **Integration Tests**: Runs against an external PostgreSQL database.

### Running Tests

To run all tests using Testcontainers, use the following command:
```bash
mvn test
```

To run integration tests with Docker Compose, follow these steps:

1. Build and run Docker containers:
   ```bash
   mvn clean package
   docker-compose up --build
   ```

2. Run the integration tests with the "integration" profile:
   ```bash
   mvn test -Dspring.profiles.active=integration
   ```

## Configuration

### Database Configuration

The application uses PostgreSQL as the database. You can configure the database settings in the `application.properties` file located in the `src/main/resources` directory.

### Testcontainers Configuration

The Testcontainers setup is configured in the test classes themselves. No additional configuration is needed to run the tests with the "test" profile.

### Docker Compose Configuration for Integration Tests

The Docker Compose setup is defined in the `docker-compose.yml` file in the root of the project. It includes services for PostgreSQL and Prometheus, as well as your Spring Boot application.

## Acknowledgments

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Testcontainers](https://www.testcontainers.org/)
- [Maven](https://maven.apache.org/)
- [Docker](https://www.docker.com/)
- [Prometheus](https://prometheus.io/)
