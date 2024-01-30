package com.cooksys.socialmedia.entities;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

import java.sql.Timestamp;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @Column(nullable = false)
    private Timestamp joined;

    // Constructors, getters, and setters

    public User() {
        // Default constructor
    }

    public User(String username, Profile profile, Timestamp joined) {
        this.username = username;
        this.profile = profile;
        this.joined = joined;
    }

    // Getters and setters for attributes

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Timestamp getJoined() {
        return joined;
    }

    public void setJoined(Timestamp joined) {
        this.joined = joined;
    }
}