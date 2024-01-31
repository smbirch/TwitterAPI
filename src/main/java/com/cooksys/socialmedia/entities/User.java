package com.cooksys.socialmedia.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_table")
@NoArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @CreationTimestamp
    private Timestamp joined;

    private boolean deleted = false;

    @Embedded
    private Credentials credentials;

    @Embedded
    private Profile profile;

    @OneToMany(mappedBy = "author")
    private List<Tweet> tweets;

    @ManyToMany
    @JoinTable(
            name = "user_likes",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "tweet_id")
    )
    private List<Tweet> likedTweets = new ArrayList<>();

    @ManyToMany(mappedBy = "mentionedUsers")
    private List<Tweet> mentionedTweets = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "followers_following")
    private List<User> followers;

    @ManyToMany(mappedBy = "followers")
    private List<User> following;

}