package com.example.repository_poc.service;

import com.example.repository_poc.repository.jpa.JpaUserRepositoryAdapter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JpaUserService extends BaseUserService<JpaUserRepositoryAdapter> {

    @Autowired
    public JpaUserService(JpaUserRepositoryAdapter userRepository, MeterRegistry meterRegistry) {
        super(userRepository, meterRegistry, "jpa.insert.counter", "jpa.update.counter", "jpa.insert.timer", "jpa.update.timer");
    }
}

