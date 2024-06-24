package com.example.repository_poc.repository;

import com.example.repository_poc.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IUserRepository<T> {
    Optional<User> findById(T id);

    Page<User> findAll(Pageable pageable);

    Long count();

    void save(User user);

    void update(User user);

    void delete(User user);
}
