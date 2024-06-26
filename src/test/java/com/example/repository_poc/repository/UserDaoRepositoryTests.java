package com.example.repository_poc.repository;

import com.example.repository_poc.Constants;
import com.example.repository_poc.model.entity.User;
import com.example.repository_poc.service.DaoUserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.stream.LongStream;


@SpringBootTest
@Testcontainers
@ActiveProfiles("test")
@EnabledIfSystemProperty(named = "spring.profiles.active", matches = "test")
@Transactional
class UserDaoRepositoryTests extends BaseRepositoryTest {

    private final DaoUserService userService;

    public UserDaoRepositoryTests(@Autowired DaoUserService userService) {
        this.userService = userService;
    }

    @Test
    @DirtiesContext
    void testDaoInserts() {
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
    void testDaoUpdates() {
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
    void testDaoFinds() {
        LongStream.range(0, Constants.ITERATIONS).parallel().forEach(userService::findById);
    }

    @Test
    @DirtiesContext
    void testDaoDeletes() {
        LongStream.range(0, Constants.ITERATIONS).parallel().forEach(i -> {
            User user = new User();
            user.setId(i);
            userService.delete(user);
        });
    }
}
