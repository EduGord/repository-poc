package com.example.demo.service;

import com.example.demo.model.entity.User;
import com.example.demo.repository.jpa.JpaUserRepository;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JpaUserService {

    private final JpaUserRepository userRepository;
    private final Counter insertCounter;
    private final Counter updateCounter;
    private final Timer insertTimer;
    private final Timer updateTimer;

    @Autowired
    public JpaUserService(JpaUserRepository userRepository, MeterRegistry meterRegistry) {
        this.userRepository = userRepository;
        this.insertCounter = meterRegistry.counter("jpa.insert.counter");
        this.updateCounter = meterRegistry.counter("jpa.update.counter");
        this.insertTimer = meterRegistry.timer("jpa.insert.timer");
        this.updateTimer = meterRegistry.timer("jpa.update.timer");
    }

    public void save(User user) {
        insertTimer.record(() -> userRepository.save(user));
        insertCounter.increment();
    }

    public void update(User user) {
        updateTimer.record(() -> userRepository.save(user));
        updateCounter.increment();
    }
}
