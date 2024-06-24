package com.example.repository_poc.service;

import com.example.repository_poc.repository.dao.DaoUserRepository;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DaoUserService extends BaseUserService<DaoUserRepository> {

    @Autowired
    public DaoUserService(DaoUserRepository userRepository, MeterRegistry meterRegistry) {
        super(userRepository, meterRegistry, "dao.insert.counter", "dao.update.counter", "dao.insert.timer", "dao.update.timer");
    }
}

