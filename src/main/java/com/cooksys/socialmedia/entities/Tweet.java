package com.mysocialmedia.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Tweet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @Column(nullable = false)
    private Timestamp posted;

    @Column
    private String content;

    @ManyToOne
    @JoinColumn(name = "in_reply_to_id")
    private Tweet inReplyTo;

    @ManyToOne
    @JoinColumn(name = "repost_of_id")
    private Tweet repostOf;

    // Constructors, getters, and setters

    public Tweet() {
        // Default constructor
    }

    public Tweet(User author, Timestamp posted, String content, Tweet inReplyTo, Tweet repostOf) {
        this.author = author;
        this.posted = posted;
        this.content = content;
        this.inReplyTo = inReplyTo;
        this.repostOf = repostOf;
    }

    // Getters and setters for attributes

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
