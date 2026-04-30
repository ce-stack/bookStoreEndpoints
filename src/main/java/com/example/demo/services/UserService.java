package com.example.demo.services;

import com.example.demo.dto.CommentRequest;
import com.example.demo.models.Book;
import com.example.demo.models.Comment;
import com.example.demo.models.User;
import com.example.demo.repositories.UserRepositoryCustom;
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
}
