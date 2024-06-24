package com.example.repository_poc.repository.jdbc;

import com.example.repository_poc.model.entity.User;
import com.example.repository_poc.model.mapper.UserRowMapper;
import com.example.repository_poc.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcUserRepository implements IUserRepository<Long> {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<User> findById(Long id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject("SELECT * FROM users WHERE id = ?", new UserRowMapper(), id));
    }

    public Page<User> findAll(Pageable pageable) {
        int offset = pageable.getPageNumber() * pageable.getPageSize();
        int limit = pageable.getPageSize();

        String query = "SELECT * FROM users ORDER BY id LIMIT ? OFFSET ?";
        List<User> users = jdbcTemplate.query(query, new UserRowMapper(), limit, offset);

        long total = count();
        return new PageImpl<>(users, pageable, total);
    }

    public Long count() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM users", Long.class);
    }

    public void save(User user) {
        Long id = jdbcTemplate.queryForObject("INSERT INTO users(name, username, password) VALUES(?, ?, ?) RETURNING id", Long.class,
                user.getName(), user.getUsername(), user.getPassword());
        user.setId(id);
    }

    public void update(User user) {
        jdbcTemplate.update("UPDATE users SET name = ?, username = ?, password = ? WHERE id = ?",
                user.getName(), user.getUsername(), user.getPassword(), user.getId());
    }

    public void delete(User user) {
        jdbcTemplate.update("DELETE FROM users WHERE id = ?", user.getId());
    }
}
