package com.example.repository_poc.model.response;

public class PerformanceTestResult {
    private long countBefore;
    private long countAfter;
    private long timeElapsed;

    public PerformanceTestResult(long countBefore, long countAfter, long timeElapsed) {
        this.countBefore = countBefore;
        this.countAfter = countAfter;
        this.timeElapsed = timeElapsed;
    }

    public long getCountBefore() {
        return countBefore;
    }

    public void setCountBefore(long countBefore) {
        this.countBefore = countBefore;
    }

    public long getCountAfter() {
        return countAfter;
    }

    public void setCountAfter(long countAfter) {
        this.countAfter = countAfter;
    }

    public long getTimeElapsed() {
        return timeElapsed;
    }

    public void setTimeElapsed(long timeElapsed) {
        this.timeElapsed = timeElapsed;
    }
}


