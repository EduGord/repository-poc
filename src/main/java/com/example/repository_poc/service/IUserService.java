package com.example.repository_poc.service;

import com.example.repository_poc.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface IUserService {
    Optional<User> findById(Long id);
    Page<User> findAll(Pageable pageable);
    Long count();
    void save(User user);
    void update(User user);
    void delete(User user);
}
