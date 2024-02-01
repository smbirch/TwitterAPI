package com.cooksys.socialmedia.services;

import com.cooksys.socialmedia.dtos.CredentialsDto;
import com.cooksys.socialmedia.dtos.TweetResponseDto;
import com.cooksys.socialmedia.dtos.UserResponseDto;

import java.util.List;

public interface TweetService {

    List<TweetResponseDto> getAllTweets();


    TweetResponseDto deleteTweetById(Long tweetId, CredentialsDto credentials);

    List<UserResponseDto> getUsersMentionedByTweetId(Long id);

}