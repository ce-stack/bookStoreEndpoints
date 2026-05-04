package com.example.demo.payload.response;

public class AuthResponse {
    private String token;
    private String message;
    private Boolean success;

    public AuthResponse(String token, String message, Boolean success) {
        this.token = token;
        this.message = message;
        this.success = success;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
