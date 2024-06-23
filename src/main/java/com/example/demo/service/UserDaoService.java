package com.example.demo.service;

import com.example.demo.model.entity.User;
import com.example.demo.repository.dao.UserDaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDaoService {
    private final UserDaoRepository userDaoRepository;

    public UserDaoService(@Autowired UserDaoRepository userDaoRepository) {
        this.userDaoRepository = userDaoRepository;
    }

    public void save(User user) {
        userDaoRepository.save(user);
    }

    public void update(User user) {
        userDaoRepository.update(user);
    }

    public User findById(Long id) {
        return userDaoRepository.findById(id);
    }

    public void delete(User user) {
        userDaoRepository.delete(user);
    }
}
