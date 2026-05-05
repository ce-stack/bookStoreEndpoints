package com.example.demo.dto.response;

import com.example.demo.models.Author;
import com.example.demo.models.Book;

import java.util.List;

public class AuthorListResponse {
    private long id;
    private String author_name;
    private List<BookDetailsResponse> books;

    public AuthorListResponse(Author author) {
        this.id = author.getId();
        this.author_name = author.getName();
        this.books = author.getBooks().stream().map(BookDetailsResponse::new).toList();
    }

    public long getId() {
        return id;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public List<BookDetailsResponse> getBooks() {
        return books;
    }
}
