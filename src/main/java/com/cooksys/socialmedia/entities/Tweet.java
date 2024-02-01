package com.cooksys.socialmedia.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Tweet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User author;

    @CreationTimestamp
    private Timestamp posted;

    private boolean deleted = false;

    private String content;

    @OneToMany(mappedBy = "inReplyTo")
    private List<Tweet> replies;

    @ManyToOne
    private Tweet inReplyTo;

    @OneToMany(mappedBy = "repostOf")
    private List<Tweet> reposts;

    @ManyToOne
    private Tweet repostOf;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "tweet_hashtags",
            joinColumns = @JoinColumn(name = "tweet_id"),
            inverseJoinColumns = @JoinColumn(name = "hashtag_id")
    )
    private List<Hashtag> hashtags = new ArrayList<>();

    @ManyToMany(mappedBy = "likedTweets")
    private List<User> likedByUsers = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "user_mentions",
            joinColumns = @JoinColumn(name = "tweet_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> mentionedUsers = new ArrayList<>();
}