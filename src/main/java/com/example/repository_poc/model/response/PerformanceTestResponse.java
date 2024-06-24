package com.example.repository_poc.model.response;

import java.io.Serial;
import java.io.Serializable;

public record PerformanceTestResponse(
        PerformanceTestResult daoResult,
        PerformanceTestResult jdbcResult,
        PerformanceTestResult jpaResult
) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
}