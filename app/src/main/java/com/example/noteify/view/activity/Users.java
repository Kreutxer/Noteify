package com.example.noteify.view.activity;

/**
 * NIM : 10120069
 * NAMA : Rendy Agustin
 * KELAS : IF-2
 */

public class Users {
    String userId, name, profile, email;

    public Users(String userId, String name, String profile, String email) {
        this.userId = userId;
        this.name = name;
        this.profile = profile;
        this.email = email;

    }

    public Users(){

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
}


/**
 * NIM : 10120069
 * NAMA : Rendy Agustin
 * KELAS : IF-2
 */