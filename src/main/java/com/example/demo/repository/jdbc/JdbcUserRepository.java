package com.example.demo.repository.jdbc;

import com.example.demo.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcUserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(User user) {
        jdbcTemplate.update("INSERT INTO public.users (name, username, password) VALUES (?, ?, ?)",
                user.getName(), user.getUsername(), user.getPassword());
    }

    public void update(User user) {
        jdbcTemplate.update("UPDATE public.users SET name = ?, username = ?, password = ? WHERE id = ?",
                user.getName(), user.getUsername(), user.getPassword(), user.getId());
    }
}
