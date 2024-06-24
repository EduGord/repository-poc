package com.example.repository_poc.service;

import com.example.repository_poc.model.RepositoryTypeEnum;
import com.example.repository_poc.model.entity.User;
import com.example.repository_poc.model.response.PerformanceTestResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.LongStream;

@Service
public class PerformanceTestService {

    public static final String INVALID_REPOSITORY_TYPE_ERROR_MESSAGE = "Invalid repository type";
    private final DaoUserService daoUserService;

    private final JdbcUserService jdbcUserService;

    private final JpaUserService jpaUserService;
    private final Logger log;

    @Autowired
    public PerformanceTestService(DaoUserService daoUserService,
                                  JdbcUserService jdbcUserService,
                                  JpaUserService jpaUserService) {
        this.daoUserService = daoUserService;
        this.jdbcUserService = jdbcUserService;
        this.jpaUserService = jpaUserService;
        this.log = LoggerFactory.getLogger(PerformanceTestService.class);
    }

    public PerformanceTestResult runTests(RepositoryTypeEnum repositoryTypeEnum,
                                          int numberOfOperations) {
        return switch (repositoryTypeEnum) {
            case DAO -> runInsertTests(daoUserService, numberOfOperations);
            case JDBC -> runInsertTests(jdbcUserService, numberOfOperations);
            case JPA -> runInsertTests(jpaUserService, numberOfOperations);
            default -> throw new IllegalArgumentException(INVALID_REPOSITORY_TYPE_ERROR_MESSAGE);
        };
    }

    public PerformanceTestResult runInsertTests(IUserService userService, int numberOfOperations) {
        long startTime;
        long endTime;
        long countBefore;
        long countAfter;

        countBefore = userService.count();
        startTime = System.currentTimeMillis();

        LongStream.range(0, numberOfOperations).parallel().forEach(i -> {
            User user = new User();
            user.setName("User" + i);
            user.setUsername("Username" + i);
            user.setPassword("Password" + i);
            userService.save(user);
        });

        countAfter = userService.count();
        endTime = System.currentTimeMillis();

        long timeElapsed = endTime - startTime;

        log.info("Before {} After {} Diff {} Time {}", countBefore, countAfter, countAfter - countBefore, timeElapsed);

        return new PerformanceTestResult(countBefore, countAfter, timeElapsed);
    }
}
