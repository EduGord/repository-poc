package com.example.repository_poc.repository;

import com.example.repository_poc.Constants;
import com.example.repository_poc.SingletonPostgreSQLContainer;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

@SpringBootTest
public abstract class BaseRepositoryTest {


    @Container
    public static PostgreSQLContainer<?> postgres = SingletonPostgreSQLContainer.getInstance();

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add(Constants.SPRING_DATASOURCE_URL_PROPERTY, postgres::getJdbcUrl);
        registry.add(Constants.SPRING_DATASOURCE_USERNAME_PROPERTY, postgres::getUsername);
        registry.add(Constants.SPRING_DATASOURCE_PASSWORD_PROPERTY, postgres::getPassword);
        registry.add(Constants.SPRING_DATASOURCE_DRIVER_CLASS_NAME_PROPERTY, postgres::getDriverClassName);
    }

    @BeforeAll
    public static void setUp() {
        postgres.start();
    }
}
