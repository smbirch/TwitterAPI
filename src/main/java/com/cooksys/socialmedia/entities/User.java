package com.cooksys.socialmedia.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "user_table")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable = false)
    @CreationTimestamp
    private Timestamp joined;

    private boolean isDeleted;

    @Embedded
    private Credentials credentials;

    @Embedded
    private Profile profile;

    @OneToMany
    private List<User> following;

    @OneToMany
    private List<User> followers;

    @OneToMany
    private List<User> userLikes;

    @OneToMany
    private List<User> userMentions;

}