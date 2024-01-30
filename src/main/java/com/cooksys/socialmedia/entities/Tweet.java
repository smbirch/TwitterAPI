package com.cooksys.socialmedia.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@Data
public class Tweet {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @NonNull
  @JoinColumn(name = "User_id")
  private User Author;

  private Timestamp posted;

  private String content;

  private Boolean deleted;

  @OneToOne
//  @JoinColumn(name = "tweet_id")
  private Tweet inReplyTo;

  @ManyToOne
//  @JoinColumn(name = "tweet_id")
  private Tweet repostOf;
}