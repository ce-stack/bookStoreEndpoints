package com.example.demo.payload.response;

public class ApiResponse {
    private String status;
    private Boolean success;
    private int code;

    public ApiResponse(String status, Boolean success, int code) {
        this.status = status;
        this.success = success;
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
