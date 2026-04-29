package com.example.demo.models;


import jakarta.persistence.*;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "comment_value")
    private String comment_value;

    @ManyToOne
    @JoinColumn(name = "user_id" , nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id" , nullable = false)
    private Book book;

    public Comment() {

    }

    public Comment(String comment_value, User user, Book book) {
        this.comment_value = comment_value;
        this.user = user;
        this.book = book;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment_value() {
        return comment_value;
    }

    public void setComment_value(String comment_value) {
        this.comment_value = comment_value;
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

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", comment_value='" + comment_value + '\'' +
                ", user=" + user +
                ", book=" + book +
                '}';
    }
}
