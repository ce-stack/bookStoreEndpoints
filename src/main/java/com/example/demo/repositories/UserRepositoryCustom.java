package com.example.demo.repositories;

import com.example.demo.dto.BuyBookRequest;
import com.example.demo.models.Book;
import com.example.demo.models.Comment;
import com.example.demo.models.Rating;
import com.example.demo.models.UserBook;

import java.util.List;

public interface UserRepositoryCustom {

    void addCommentToBook(Comment comment);

    void addRatingToBook(Rating rating);

    void updateComment(Comment comment , int id);

    Comment findCommentById(int id);

    List<Book> SearchBook(String value);

    void userBuyBook(UserBook userBook);

}
