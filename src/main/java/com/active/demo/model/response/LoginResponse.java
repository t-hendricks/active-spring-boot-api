package com.active.demo.model.response;

public class LoginResponse {
    private String message;

    public LoginResponse(String message) {
        this.message = message;
    }

    // getter
    public String getMessage() {
        return message;
    }

    // setter
    public void setMessage(String message) {
        this.message = message;
    }
}
