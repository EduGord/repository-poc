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
   cd repository-poc
   ```

2. Build the project:
   ```bash
   mvn clean package -Dmaven.test.skip=true
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

1. Generate the JAR file:
   ```bash
   mvn clean package -Dmaven.test.skip=true
   ```

2. Build the Docker images and start the containers using Docker Compose:
   ```bash
   docker-compose up --build
   ```

3. Run the integration tests:
   ```bash
   mvn test -Dspring.profiles.active=integration
   ```

### Performance Tests

To run performance tests for 100,000 inserts and updates across JDBC, JPA, and DAO implementations, use the following endpoint:

1. Start your application:
   ```bash
   java -jar target/repository-poc.jar
   ```

2. Trigger the performance tests by making a POST request to:
   ```
   POST http://127.0.0.1:8080/performance/insert-test?numberOfOperations=100000
   ```
      ```
   POST http://127.0.0.1:8080/performance/select-test?numberOfOperations=100000
   ```
      ```
   POST http://127.0.0.1:8080/performance/update-test?numberOfOperations=100000
   ```
      ```
   POST http://127.0.0.1:8080/performance/delete-test?numberOfOperations=100000
   ```

   Replace `8080` with your application's port if different.

   This endpoint


## Configuration

### Database Configuration

The application uses PostgreSQL as the database. You can configure the database settings in the `application-integration.yaml` file located in the `src/main/resources` directory.

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
