package com.example.demo.models;


import jakarta.persistence.*;

@Entity
@Table(name = "user_books")
public class UserBook {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id" , nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id" , nullable = false)
    private Book book;

    @Column(name = "price")
    private double price;

    public UserBook() {

    }

    public UserBook(User user, Book book, double price) {
        this.user = user;
        this.book = book;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
