package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;

public class BuyBookRequest {

    @NotNull(message = "user is required")
    private int user_id;

    @NotNull(message = "book is required")
    private int book_id;

    @NotNull(message = "price is required")
    private double price;

    public BuyBookRequest() {

    }

    public BuyBookRequest(int book_id, double price, int user_id) {
        this.book_id = book_id;
        this.price = price;
        this.user_id = user_id;
    }


    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }
}
