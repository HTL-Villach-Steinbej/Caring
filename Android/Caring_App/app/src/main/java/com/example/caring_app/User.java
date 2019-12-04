package com.example.caring_app;

public class User {
    private String email;
    private String FullName;
    private String paymethod;

    public User(String fullName,String email ,String paymethod) {
        this.email = email;
        FullName = fullName;
        this.paymethod = paymethod;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getPaymethod() {
        return paymethod;
    }

    public void setPaymethod(String paymethod) {
        this.paymethod = paymethod;
    }
}
