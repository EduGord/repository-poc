package com.example.repository_poc.controller;

import com.example.repository_poc.model.PerformanceOperationEnum;
import com.example.repository_poc.model.response.PerformanceTestResponse;
import com.example.repository_poc.service.PerformanceTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/performance")
public class PerformanceTestController {

    private final PerformanceTestService performanceTestService;

    public PerformanceTestController(@Autowired PerformanceTestService performanceTestService) {
        this.performanceTestService = performanceTestService;
    }

    @PostMapping("/{performanceOperationEnum}")
    public ResponseEntity<PerformanceTestResponse> runSelectsTest(@RequestParam int numberOfOperations,
                                                                  @PathVariable PerformanceOperationEnum performanceOperationEnum) {
        return ResponseEntity.ok(performanceTestService.runTests(performanceOperationEnum, numberOfOperations));
    }
}
