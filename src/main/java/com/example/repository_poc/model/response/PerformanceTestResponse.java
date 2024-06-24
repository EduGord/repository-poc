package com.example.repository_poc.model.response;

public class PerformanceTestResponse {
    private PerformanceTestResult daoResult;
    private PerformanceTestResult jdbcResult;
    private PerformanceTestResult jpaRepositoryResult;

    public PerformanceTestResponse(PerformanceTestResult daoResult, PerformanceTestResult jdbcResult, PerformanceTestResult jpaRepositoryResult) {
        this.daoResult = daoResult;
        this.jdbcResult = jdbcResult;
        this.jpaRepositoryResult = jpaRepositoryResult;
    }

    public PerformanceTestResult getDaoResult() {
        return daoResult;
    }

    public void setDaoResult(PerformanceTestResult daoResult) {
        this.daoResult = daoResult;
    }

    public PerformanceTestResult getJdbcResult() {
        return jdbcResult;
    }

    public void setJdbcResult(PerformanceTestResult jdbcResult) {
        this.jdbcResult = jdbcResult;
    }

    public PerformanceTestResult getJpaRepositoryResult() {
        return jpaRepositoryResult;
    }

    public void setJpaRepositoryResult(PerformanceTestResult jpaRepositoryResult) {
        this.jpaRepositoryResult = jpaRepositoryResult;
    }
}