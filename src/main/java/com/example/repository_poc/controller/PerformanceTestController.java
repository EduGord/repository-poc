package com.example.repository_poc.controller;

import com.example.repository_poc.model.RepositoryTypeEnum;
import com.example.repository_poc.model.response.PerformanceTestResponse;
import com.example.repository_poc.model.response.PerformanceTestResult;
import com.example.repository_poc.service.PerformanceTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/performance")
public class PerformanceTestController {

    private PerformanceTestService performanceTestService;

    public PerformanceTestController(@Autowired PerformanceTestService performanceTestService) {
        this.performanceTestService = performanceTestService;
    }

    @PostMapping("/test")
    public ResponseEntity<PerformanceTestResponse> runPerformanceTests(@RequestParam int numberOfOperations) {
        PerformanceTestResult daoResult = performanceTestService.runTests(RepositoryTypeEnum.DAO, numberOfOperations);
        PerformanceTestResult jdbcResult = performanceTestService.runTests(RepositoryTypeEnum.JDBC, numberOfOperations);
        PerformanceTestResult jpaRepositoryResult = performanceTestService.runTests(RepositoryTypeEnum.JPA, numberOfOperations);

        PerformanceTestResponse response = new PerformanceTestResponse(daoResult, jdbcResult, jpaRepositoryResult);

        return ResponseEntity.ok(response);
    }
}
