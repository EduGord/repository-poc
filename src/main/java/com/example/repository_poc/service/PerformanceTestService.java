package com.example.repository_poc.service;

import com.example.repository_poc.model.PerformanceOperationEnum;
import com.example.repository_poc.model.entity.User;
import com.example.repository_poc.model.response.PerformanceTestResponse;
import com.example.repository_poc.model.response.PerformanceTestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.stream.LongStream;

@Service
public class PerformanceTestService {

    private final DaoUserService daoUserService;
    private final JdbcUserService jdbcUserService;
    private final JpaUserService jpaUserService;

    @Autowired
    public PerformanceTestService(DaoUserService daoUserService,
                                  JdbcUserService jdbcUserService,
                                  JpaUserService jpaUserService) {
        this.daoUserService = daoUserService;
        this.jdbcUserService = jdbcUserService;
        this.jpaUserService = jpaUserService;
    }

    public PerformanceTestResponse runTests(PerformanceOperationEnum performanceOperationEnum,
                                          int numberOfOperations) {
        switch (performanceOperationEnum) {
            case INSERT -> {
                var daoResult = runInsertTest(daoUserService, numberOfOperations);
                var jdbcResult = runInsertTest(jdbcUserService, numberOfOperations);
                var jpaResult = runInsertTest(jpaUserService, numberOfOperations);
                return new PerformanceTestResponse(daoResult, jdbcResult, jpaResult);
            }
            case SELECT -> {
                var daoResult = runSelectTest(daoUserService, numberOfOperations);
                var jdbcResult = runSelectTest(jdbcUserService, numberOfOperations);
                var jpaResult = runSelectTest(jpaUserService, numberOfOperations);
                return new PerformanceTestResponse(daoResult, jdbcResult, jpaResult);
            }
            case UPDATE -> {
                var daoResult = runUpdateTest(daoUserService, numberOfOperations);
                var jdbcResult = runUpdateTest(jdbcUserService, numberOfOperations);
                var jpaResult = runUpdateTest(jpaUserService, numberOfOperations);
                return new PerformanceTestResponse(daoResult, jdbcResult, jpaResult);
            }
            case DELETE -> {
                var daoResult = runDeleteTest(daoUserService, numberOfOperations);
                var jdbcResult = runDeleteTest(jdbcUserService, numberOfOperations);
                var jpaResult = runDeleteTest(jpaUserService, numberOfOperations);
                return new PerformanceTestResponse(daoResult, jdbcResult, jpaResult);
            }
            default -> throw new IllegalArgumentException("Invalid performance operation type");
        }
    }

    public PerformanceTestResult runInsertTest(IUserService userService, long numberOfOperations) {
        long startTime;
        long countBefore;

        countBefore = userService.count();
        startTime = System.nanoTime();

        LongStream.range(0, numberOfOperations).parallel().forEach(i -> {
            User user = new User();
            user.setName("User" + i);
            user.setUsername("Username" + i);
            user.setPassword("Password" + i);
            userService.save(user);
        });

        return getPerformanceTestResult(userService, numberOfOperations, startTime, countBefore);
    }

    private PerformanceTestResult runSelectTest(IUserService userService, long numberOfOperations) {
        long startTime;
        long count;

        count = userService.count();
        startTime = System.nanoTime();

        LongStream.range(0, numberOfOperations).parallel().forEach(userService::findById);

        return getPerformanceTestResult(userService, numberOfOperations, startTime, count);
    }

    @NonNull
    private PerformanceTestResult getPerformanceTestResult(IUserService userService, long numberOfOperations, long startTime, long countBefore) {
        long countAfter;
        long endTime;
        long successfulOperations;
        long failedOperations;
        countAfter = userService.count();
        endTime = System.nanoTime();
        Duration timeElapsed = Duration.ofNanos(endTime - startTime);
        successfulOperations = countAfter - countBefore;
        failedOperations = numberOfOperations - successfulOperations;

        return new PerformanceTestResult(numberOfOperations, successfulOperations, failedOperations, countBefore, countAfter, timeElapsed);
    }

    private PerformanceTestResult runUpdateTest(IUserService userService, long numberOfOperations) {
        long startTime;
        long countBefore;

        countBefore = userService.count();
        startTime = System.nanoTime();

        LongStream.range(0, numberOfOperations).parallel().forEach(i -> {
            User user = new User();
            user.setId(i);
            user.setName("UpdatedUser" + i);
            user.setUsername("UpdatedUsername" + i);
            user.setPassword("UpdatedPassword" + i);
            userService.update(user);
        });

        return getPerformanceTestResult(userService, numberOfOperations, startTime, countBefore);

    }

    private PerformanceTestResult runDeleteTest(IUserService userService, int numberOfOperations) {
        long startTime;
        long countBefore;

        List<Long> ids = userService.findAll(Pageable.ofSize(numberOfOperations))
                .stream()
                .map(User::getId)
                .toList();

        countBefore = userService.count();
        startTime = System.nanoTime();

        ids.parallelStream().forEach(id -> {
            User user = new User();
            user.setId(id);
            userService.delete(user);
        });

        return getPerformanceTestResult(userService, -numberOfOperations, startTime, countBefore);
    }
}
