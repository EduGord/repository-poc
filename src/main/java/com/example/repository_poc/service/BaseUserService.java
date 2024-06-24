package com.example.repository_poc.service;

import com.example.repository_poc.model.entity.User;
import com.example.repository_poc.repository.IUserRepository;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public abstract class BaseUserService<R extends IUserRepository<Long>> implements IUserService {

    protected final R userRepository;
    private final Counter insertCounter;
    private final Counter updateCounter;
    private final Timer insertTimer;
    private final Timer updateTimer;

    protected BaseUserService(R userRepository, MeterRegistry meterRegistry, String insertCounterName, String updateCounterName, String insertTimerName, String updateTimerName) {
        this.userRepository = userRepository;
        this.insertCounter = meterRegistry.counter(insertCounterName);
        this.updateCounter = meterRegistry.counter(updateCounterName);
        this.insertTimer = meterRegistry.timer(insertTimerName);
        this.updateTimer = meterRegistry.timer(updateTimerName);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public Long count() {
        return userRepository.count();
    }

    public void save(User user) {
        insertTimer.record(() -> userRepository.save(user));
        insertCounter.increment();
    }

    public void update(User user) {
        updateTimer.record(() -> userRepository.update(user));
        updateCounter.increment();
    }

    public void delete(User user) {
        userRepository.delete(user);
    }
}
