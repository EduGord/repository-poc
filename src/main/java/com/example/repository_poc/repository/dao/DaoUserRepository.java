package com.example.repository_poc.repository.dao;

import com.example.repository_poc.model.entity.User;
import com.example.repository_poc.repository.IUserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
@Transactional
public class DaoUserRepository implements IUserRepository<Long> {

    @PersistenceContext
    private final EntityManager entityManager;

    public DaoUserRepository(@Autowired EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Optional<User> findById(Long id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }

    public Page<User> findAll(Pageable pageable) {
        List<User> users = entityManager.createQuery("SELECT u FROM User u", User.class)
                .setFirstResult(pageable.getPageNumber() * pageable.getPageSize())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        return new PageImpl<>(users, pageable, count());
    }

    public Long count() {
        return entityManager.createQuery("SELECT COUNT(u) FROM User u", Long.class).getSingleResult();
    }

    public void save(User user) {
        entityManager.persist(user);
    }

    public void update(User user) {
        entityManager.merge(user);
    }

    public void delete(User user) {
        boolean exists = !entityManager.createQuery("SELECT 1 FROM User u WHERE u.id = :id", Integer.class)
                .setParameter("id", user.getId())
                .setMaxResults(1)
                .getResultList()
                .isEmpty();
        if (exists) {
            entityManager.remove(user);
        }
    }
}
