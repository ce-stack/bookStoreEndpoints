package com.example.demo.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RatingRequest {

    @NotBlank(message = "value is required")
    @Size(min = 1 , max = 5)
    private String value;

    @NotNull(message = "user is required")
    private Integer user_id;

    @NotNull(message = "book is required")
    private Integer book_id;

    public RatingRequest() {

    }

    public RatingRequest(String value, Integer user_id, Integer book_id) {
        this.value = value;
        this.user_id = user_id;
        this.book_id = book_id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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
