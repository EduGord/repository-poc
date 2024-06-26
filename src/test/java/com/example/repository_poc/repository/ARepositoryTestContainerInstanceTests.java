package com.example.repository_poc.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

/**
 * Verifies the availability of the PostgreSQL container.
 * <br>
 * This test ensures that the PostgreSQL container, prefixed with 'A' for prioritization,
 * starts before other tests to accurately measure execution time.
 * It serves as a prerequisite for tests dependent on a running PostgreSQL instance.
 * The container is initialized once and shared across tests to optimize performance,
 * avoiding multiple startup delays. Post-test, stopping and removing the container
 * further streamlines test execution by managing shared resources.
 */
@SpringBootTest
@ActiveProfiles("test")
@EnabledIfSystemProperty(named = "spring.profiles.active", matches = "test")
@Testcontainers
class ARepositoryTestContainerInstanceTests extends BaseRepositoryTest {

    @Test
    void testPostgreSQLContainer() {
        assert postgres.isRunning();
    }
}