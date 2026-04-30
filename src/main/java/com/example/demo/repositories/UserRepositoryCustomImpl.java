package com.example.demo.repositories;

import com.example.demo.models.Comment;
import com.example.demo.models.Rating;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryCustomImpl implements UserRepositoryCustom{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void addCommentToBook(Comment comment) {
        entityManager.persist(comment);
    }

    @Override
    @Transactional
    public void addRatingToBook(Rating rating) {
        entityManager.persist(rating);
    }
}
