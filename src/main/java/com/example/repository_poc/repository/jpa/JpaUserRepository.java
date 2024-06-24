package com.example.repository_poc.repository.jpa;

import com.example.repository_poc.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface JpaUserRepository extends JpaRepository<User, Long>, PagingAndSortingRepository<User, Long> {
}
