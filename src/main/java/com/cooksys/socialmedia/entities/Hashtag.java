package com.cooksys.socialmedia.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.springframework.data.annotation.Id;

import java.sql.Timestamp;

@Entity
public class Hashtag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String label;

    @Column(nullable = false)
    private Timestamp firstUsed;

    @Column(nullable = false)
    private Timestamp lastUsed;

    // Constructors, getters, and setters

    public Hashtag() {
        // Default constructor
    }

    public Hashtag(String label, Timestamp firstUsed, Timestamp lastUsed) {
        this.label = label;
        this.firstUsed = firstUsed;
        this.lastUsed = lastUsed;
    }

    // Getters and setters for attributes

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Timestamp getFirstUsed() {
        return firstUsed;
    }

    public void setFirstUsed(Timestamp firstUsed) {
        this.firstUsed = firstUsed;
    }

    public Timestamp getLastUsed() {
        return lastUsed;
    }

    public void setLastUsed(Timestamp lastUsed) {
        this.lastUsed = lastUsed;
    }
}