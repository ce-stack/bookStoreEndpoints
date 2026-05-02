package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;

public class BuyBookRequest {

    @NotNull(message = "user is required")
    private int user_id;

    @NotNull(message = "book is required")
    private Long book_id;

    public BuyBookRequest() {

    }

    public BuyBookRequest(int user_id, Long book_id) {
        this.user_id = user_id;
        this.book_id = book_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Long getBook_id() {
        return book_id;
    }

    public void setBook_id(Long book_id) {
        this.book_id = book_id;
    }

    @Override
    public String toString() {
        return "BuyBookRequest{" +
                "user_id=" + user_id +
                ", book_id=" + book_id +
                '}';
    }
}
