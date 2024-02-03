package com.cooksys.socialmedia.repositories;

import com.cooksys.socialmedia.entities.Tweet;
import com.cooksys.socialmedia.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long> {

    List<Tweet> findByDeletedFalse();

    List<Tweet> findByDeletedFalseOrderByPostedDesc();

    List<Tweet> findByInReplyToAndInReplyToIsNotNull(Tweet parentTweet);
    
    List<Tweet> findByMentionedUsersAndDeletedFalseOrderByPostedDesc(User user);
    
    List<Tweet> findByInReplyToIdAndDeletedFalse(Long inReplyToId);


}
