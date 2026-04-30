package com.example.demo.repositories;

import com.example.demo.models.Comment;
import com.example.demo.models.Rating;

public interface UserRepositoryCustom {

    void addCommentToBook(Comment comment);

    void addRatingToBook(Rating rating);

    void updateComment(Comment comment , int id);

    Comment findCommentById(int id);
}
