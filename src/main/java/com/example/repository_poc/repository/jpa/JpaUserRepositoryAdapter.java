package com.example.repository_poc.repository.jpa;

import com.example.repository_poc.model.entity.User;
import com.example.repository_poc.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JpaUserRepositoryAdapter implements IUserRepository<Long> {

    private final JpaUserRepository jpaUserRepository;

    @Autowired
    public JpaUserRepositoryAdapter(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public Optional<User> findById(Long id) {
        return jpaUserRepository.findById(id);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return jpaUserRepository.findAll(pageable);
    }

    @Override
    public Long count() {
        return jpaUserRepository.count();
    }

    @Override
    public void save(User user) {
        jpaUserRepository.save(user);
    }

    @Override
    public void update(User user) {
        jpaUserRepository.save(user);
    }

    @Override
    public void delete(User user) {
        jpaUserRepository.delete(user);
    }
}
