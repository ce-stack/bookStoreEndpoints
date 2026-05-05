package com.example.demo.dto.response;

import com.example.demo.models.Category;

import java.util.List;

public class CategoryResponse {
    private long id;
    private String category_name;
    private List<BookDetailsResponse> books;

    public CategoryResponse(Category category) {
        this.id = category.getId();
        this.category_name = category.getName();
        this.books = category.getBooks().stream().map(BookDetailsResponse::new).toList();
    }

    public long getId() {
        return id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public List<BookDetailsResponse> getBooks() {
        return books;
    }
}

