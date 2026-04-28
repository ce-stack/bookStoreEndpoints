package com.example.demo.models;


import jakarta.persistence.*;

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

    public Book() {

    }


    public Book(String num_page, String book_name) {
        this.num_page = num_page;
        this.book_name = book_name;
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

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", book_name='" + book_name + '\'' +
                ", num_page='" + num_page + '\'' +
                ", author=" + author +
                ", category=" + category +
                '}';
    }
}
