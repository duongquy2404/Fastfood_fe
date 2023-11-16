package com.example.fastfood.data.reponse;

import com.example.fastfood.data.model.User;

public class UserResponse {
    private User user;
    private String message;

    public UserResponse() {
    }

    public UserResponse(User user, String message) {
        this.user = user;
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
