package com.cooksys.socialmedia.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class Credentials implements Serializable {
    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    // Constructors, getters, and setters

    public Credentials() {
        // Default constructor
    }

    public Credentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters and setters for attributes

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}