package com.example.demo.service;

import com.example.demo.model.entity.User;
import com.example.demo.repository.jdbc.JdbcUserRepository;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JdbcUserService {

    private final JdbcUserRepository userRepository;
    private final Counter insertCounter;
    private final Counter updateCounter;
    private final Timer insertTimer;
    private final Timer updateTimer;

    @Autowired
    public JdbcUserService(JdbcUserRepository userRepository, MeterRegistry meterRegistry) {
        this.userRepository = userRepository;
        this.insertCounter = meterRegistry.counter("jdbc.insert.counter");
        this.updateCounter = meterRegistry.counter("jdbc.update.counter");
        this.insertTimer = meterRegistry.timer("jdbc.insert.timer");
        this.updateTimer = meterRegistry.timer("jdbc.update.timer");
    }

    public void save(User user) {
        insertTimer.record(() -> userRepository.save(user));
        insertCounter.increment();
    }

    public void update(User user) {
        updateTimer.record(() -> userRepository.update(user));
        updateCounter.increment();
    }
}
