package com.example.repository_poc;

import com.example.repository_poc.model.entity.User;
import com.example.repository_poc.service.JdbcUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.stream.LongStream;


@SpringBootTest
@ActiveProfiles("test")
@EnabledIfSystemProperty(named = "spring.profiles.active", matches = "test")
@Testcontainers
class JdbcUserRepositoryTests {

    private final JdbcUserService userService;

    public JdbcUserRepositoryTests(@Autowired JdbcUserService userService) {
        this.userService = userService;
    }
    
    @Container
    public static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest")
            .withDatabaseName("testdb")
            .withUsername("testuser")
            .withPassword("testpassword")
            .withInitScript("schema.sql");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @BeforeEach
    public void setup() {
    }

    @Test
    @DirtiesContext
    void testJdbcInserts() {
        LongStream.range(0, Constants.ITERATIONS).parallel().forEach(i -> {
            User user = new User();
            user.setName("User" + i);
            user.setUsername("Username" + i);
            user.setPassword("Password" + i);
            userService.save(user);
        });
    }

    @Test
    @DirtiesContext
    void testJdbcUpdates() {
        LongStream.range(0, Constants.ITERATIONS).parallel().forEach(i -> {
            User user = new User();
            user.setName("UpdatedUser" + i);
            user.setUsername("UpdatedUsername" + i);
            user.setPassword("UpdatedPassword" + i);
            userService.update(user);
        });
    }

    @Test
    @DirtiesContext
    void testJdbcSelects() {
        LongStream.range(0, Constants.ITERATIONS).parallel().forEach(userService::findById);
    }

    @Test
    @DirtiesContext
    void testJdbcDeletes() {
        LongStream.range(0, Constants.ITERATIONS).parallel().forEach(i -> {
            User user = new User();
            user.setId(i);
            userService.delete(user);
        });
    }
}
