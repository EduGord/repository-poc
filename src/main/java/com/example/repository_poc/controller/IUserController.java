package com.example.repository_poc.controller;

import com.example.repository_poc.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


public interface IUserController {
    @GetMapping("/{id}")
    ResponseEntity<User> findById(@PathVariable Long id);

    @GetMapping("/all")
    ResponseEntity<Page<User>> findAll(Pageable pageable);

    @GetMapping("/count")
    ResponseEntity<Long> count();

    @PostMapping
    ResponseEntity<Void> save(User user);

    @PutMapping
    ResponseEntity<Void> update(User user);

    @DeleteMapping
    ResponseEntity<Void> delete(User user);
}