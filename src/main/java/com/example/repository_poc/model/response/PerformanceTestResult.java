package com.example.repository_poc.model.response;

import java.io.Serial;
import java.io.Serializable;
import java.time.Duration;

public record PerformanceTestResult(
        Long numberOfOperations,
        Long successfulOperations,
        Long failedOperations,
        Long countBefore,
        Long countAfter,
        Duration timeElapsed
) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
}
