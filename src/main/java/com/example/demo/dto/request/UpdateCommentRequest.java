package com.example.demo.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UpdateCommentRequest {

    @NotNull(message = "user is required")
    private int id;

    @NotBlank(message = "comment value is required")
    @Size(min = 3 , max = 70)
    private String comment_value;

    public UpdateCommentRequest() {

    }

    public UpdateCommentRequest(int id, String comment_value) {
        this.id = id;
        this.comment_value = comment_value;
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
}
