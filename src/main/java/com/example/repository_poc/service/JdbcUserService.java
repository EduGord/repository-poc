package com.example.repository_poc.service;

import com.example.repository_poc.repository.jdbc.JdbcUserRepository;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JdbcUserService extends BaseUserService<JdbcUserRepository> {

    @Autowired
    public JdbcUserService(JdbcUserRepository userRepository, MeterRegistry meterRegistry) {
        super(userRepository, meterRegistry, "jdbc.insert.counter", "jdbc.update.counter", "jdbc.insert.timer", "jdbc.update.timer");
    }
}
