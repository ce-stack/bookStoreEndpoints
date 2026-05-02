package com.example.demo.models;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "book_name")
    private String book_name;

    @Column(name = "num_page")
    private String num_page;

    @ManyToOne
    @JoinColumn(name = "author_id" , nullable = false)
    private Author author;

    @ManyToOne
    @JoinColumn(name = "category_id" , nullable = false)
    private Category category;

    @Column(name = "stock")
    private int stock;

    @Column(name = "price")
    private double price;


    @OneToMany(mappedBy = "book")
    private List<UserBook> userBooks;

    public Book() {

    }


    public Book(String book_name, String num_page, Author author, Category category, int stock , double price) {
        this.book_name = book_name;
        this.num_page = num_page;
        this.author = author;
        this.category = category;
        this.stock = stock;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getNum_page() {
        return num_page;
    }

    public void setNum_page(String num_page) {
        this.num_page = num_page;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<UserBook> getUserBooks() {
        return userBooks;
    }

    public void setUserBooks(List<UserBook> userBooks) {
        this.userBooks = userBooks;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", book_name='" + book_name + '\'' +
                ", num_page='" + num_page + '\'' +
                ", author=" + author +
                ", category=" + category +
                ", stock=" + stock +
                ", price=" + price +
                ", userBooks=" + userBooks +
                '}';
    }
}
