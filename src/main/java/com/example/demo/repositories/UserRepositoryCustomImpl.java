package com.example.demo.repositories;

import com.example.demo.dto.UpdateCommentRequest;
import com.example.demo.models.Book;
import com.example.demo.models.Comment;
import com.example.demo.models.Rating;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Override
    public void updateComment(Comment comment, int id) {
        entityManager.find(Comment.class , id);
    }


    @Override
    public Comment findCommentById(int id) {
      return entityManager.find(Comment.class , id);
    }

    @Override
    public List<Book> SearchBook(String value) {

        String jpql = "SELECT b FROM Book b WHERE LOWER(b.book_name) LIKE LOWER(:value)";
                return entityManager
                .createQuery(jpql , Book.class)
                .setParameter("value" , "%" + value + "%")
                .getResultList();
    }




}
