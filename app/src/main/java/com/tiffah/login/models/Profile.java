package com.tiffah.login.models;

public class Profile {
    private String username;
    private String email;
    private String phone;
    private String location;


    public Profile(String location) {
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.location = location;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
