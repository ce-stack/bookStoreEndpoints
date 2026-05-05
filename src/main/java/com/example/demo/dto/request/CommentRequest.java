package com.example.demo.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CommentRequest {

    @NotBlank(message = "the comment is empty")
    @Size(min = 4 , max = 100)
    private String comment_value;

    @NotNull(message = "the user is empty")
    private Integer user_id;

    @NotNull(message = "the book is empty")
    private Integer book_id;


    public CommentRequest() {

    }

    public CommentRequest(int user_id, int book_id, String comment_value) {
        this.user_id = user_id;
        this.book_id = book_id;
        this.comment_value = comment_value;
    }

    public String getComment_value() {
        return comment_value;
    }

    public void setComment_value(String comment_value) {
        this.comment_value = comment_value;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getBook_id() {
        return book_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }
}
