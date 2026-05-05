package com.example.demo.dto.response;

import com.example.demo.models.Book;

public class BookDetailsResponse {
    private long id;
    private String book_name;
    private String num_page;
    private String categoryName;
    private String authorName;

    public BookDetailsResponse(Book book) {
        this.id = book.getId();
        this.book_name = book.getBook_name();
        this.num_page = book.getNum_page();
        this.categoryName = book.getCategory().getName();
        this.authorName = book.getAuthor().getName();
    }

    public long getId() {
        return id;
    }

    public String getBook_name() {
        return book_name;
    }

    public String getNum_page() {
        return num_page;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getAuthorName() {
        return authorName;
    }
}
