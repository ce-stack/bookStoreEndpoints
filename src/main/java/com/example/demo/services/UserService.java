package com.example.demo.services;

import com.example.demo.dto.CommentRequest;
import com.example.demo.dto.RatingRequest;
import com.example.demo.dto.UpdateCommentRequest;
import com.example.demo.models.Book;
import com.example.demo.models.Comment;
import com.example.demo.models.Rating;
import com.example.demo.models.User;
import com.example.demo.repositories.UserRepositoryCustom;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepositoryCustom userRepositoryCustom;

    public UserService(UserRepositoryCustom userRepositoryCustom) {
        this.userRepositoryCustom = userRepositoryCustom;
    }

    public String userCommentToBook(CommentRequest request) {
        Comment comment = new Comment();

        comment.setComment_value(request.getComment_value());

        Book book = new Book();
        book.setId(request.getBook_id());

        User user = new User();
        user.setId(request.getUser_id());

        comment.setBook(book);
        comment.setUser(user);
        userRepositoryCustom.addCommentToBook(comment);

        return "comment added success";

    }

    public String userRatingToBook(RatingRequest request) {
        Rating rating = new Rating();

        rating.setValue(request.getValue());

        Book book = new Book();
        book.setId(request.getBook_id());

        User user = new User();
        user.setId(request.getUser_id());

        rating.setBook(book);
        rating.setUser(user);

        userRepositoryCustom.addRatingToBook(rating);

        return "rating added success";

    }

    @Transactional
    public String userUpdateComment(UpdateCommentRequest request, int id) {

        Comment comment = userRepositoryCustom.findCommentById(id);

        if (comment == null) {
            throw new RuntimeException("comment not found");
        }

        comment.setComment_value(request.getComment_value());

        return "comment updated success";
    }
}
