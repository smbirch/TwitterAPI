package com.cooksys.socialmedia.services;

import com.cooksys.socialmedia.dtos.ContextDto;
import com.cooksys.socialmedia.dtos.CredentialsDto;
import java.util.List;
import com.cooksys.socialmedia.dtos.TweetRequestDto;
import com.cooksys.socialmedia.dtos.TweetResponseDto;
import com.cooksys.socialmedia.dtos.UserResponseDto;

import java.util.List;

public interface TweetService {

    List<TweetResponseDto> getAllTweets();

    TweetResponseDto deleteTweetById(Long tweetId, CredentialsDto credentials);

    List<UserResponseDto> getUsersMentionedByTweetId(Long id);

    TweetResponseDto getTweetById(Long id);

    List<TweetResponseDto> getRepostsById(Long id);

    List<UserResponseDto> getLikesById(Long id);

    TweetResponseDto createReply(Long id, TweetRequestDto tweetRequest);

    TweetResponseDto postTweet(TweetRequestDto tweetRequest);

}