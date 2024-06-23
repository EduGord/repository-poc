package com.example.demo.config.jpa;

import com.example.demo.repository.jpa.JpaUserRepository;
import com.example.demo.service.JpaUserService;
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
    public JpaUserService jpaUserService(JpaUserRepository userRepository) {
        return new JpaUserService(userRepository, meterRegistry);
    }
}
