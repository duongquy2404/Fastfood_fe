package com.example.fastfood.data.reponse;

import com.example.fastfood.data.model.Admin;

public class AdminResponse {
    private Admin admin;
    private String message;

    public AdminResponse() {
    }

    public AdminResponse(Admin admin, String message) {
        this.admin = admin;
        this.message = message;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
