package com.example.repository_poc.config.jdbc;

import com.example.repository_poc.repository.jdbc.JdbcUserRepository;
import com.example.repository_poc.service.JdbcUserService;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class JdbcMetricsConfiguration {

    private final MeterRegistry meterRegistry;

    @Autowired
    public JdbcMetricsConfiguration(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @Bean
    public JdbcUserService jdbcUserService(JdbcUserRepository userRepository) {
        return new JdbcUserService(userRepository, meterRegistry);
    }
}
