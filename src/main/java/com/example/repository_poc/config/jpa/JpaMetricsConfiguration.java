package com.example.repository_poc.config.jpa;

import com.example.repository_poc.repository.jpa.JpaUserRepositoryAdapter;
import com.example.repository_poc.service.JpaUserService;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class JpaMetricsConfiguration {

    private final MeterRegistry meterRegistry;

    @Autowired
    public JpaMetricsConfiguration(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @Bean
    public JpaUserService jpaUserService(JpaUserRepositoryAdapter userRepository) {
        return new JpaUserService(userRepository, meterRegistry);
    }
}
