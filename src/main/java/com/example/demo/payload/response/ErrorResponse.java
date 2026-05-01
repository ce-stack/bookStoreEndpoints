package com.example.demo.payload.response;

public class ErrorResponse {

    private String message;

    private int status;

    private long timestamp;

    public ErrorResponse() {

    }

    public ErrorResponse(String message, int status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
