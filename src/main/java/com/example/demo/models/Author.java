package com.example.demo.models;

import jakarta.persistence.*;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    public Author() {

    }

    public Author(String name) {
        this.name = name;
    }
}
