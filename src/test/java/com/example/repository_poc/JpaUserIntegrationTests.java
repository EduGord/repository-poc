package com.example.repository_poc;

import com.example.repository_poc.model.entity.User;
import com.example.repository_poc.service.JpaUserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.LongStream;

@SpringBootTest
@ActiveProfiles("integration")
@EnabledIfSystemProperty(named = "spring.profiles.active", matches = "integration")
@Transactional
class JpaUserIntegrationTests {

    private final JpaUserService userService;

    @Autowired
    public JpaUserIntegrationTests(JpaUserService userService) {
        this.userService = userService;
    }

    @Test
    @DirtiesContext
    void testJpaInserts() {
        LongStream.range(0, 100_000).parallel().forEach(i -> {
            User user = new User();
            user.setName("User" + i);
            user.setUsername("Username" + i);
            user.setPassword("Password" + i);
            userService.save(user);
        });
    }

    @Test
    @DirtiesContext
    void testJpaUpdates() {
        LongStream.range(0, 100_000).parallel().forEach(i -> {
            User user = new User();
            user.setName("UpdatedUser" + i);
            user.setUsername("UpdatedUsername" + i);
            user.setPassword("UpdatedPassword" + i);
            userService.update(user);
        });
    }
}
