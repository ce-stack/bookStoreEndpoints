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




}
