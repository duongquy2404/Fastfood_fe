package com.example.fastfood.data.reponse;

public class UserResponse {
    String token;
    String message;

    public UserResponse() {
    }

    public UserResponse(String token, String message) {
        this.token = token;
        this.message = message;
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
}
