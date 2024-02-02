package com.cooksys.socialmedia.services;

import com.cooksys.socialmedia.dtos.*;
import com.cooksys.socialmedia.entities.Credentials;
import java.util.List;

public interface TweetService {

    List<TweetResponseDto> getAllTweets();

    TweetResponseDto deleteTweetById(Long tweetId, CredentialsDto credentials);

    List<UserResponseDto> getUsersMentionedByTweetId(Long id);

    TweetResponseDto getTweetById(Long id);

    List<TweetResponseDto> getRepostsById(Long id);

    List<UserResponseDto> getLikesById(Long id);

    TweetResponseDto createReply(Long id, TweetRequestDto tweetRequest);

    TweetResponseDto createRepost(Long id, Credentials tweetRequestDto);

    ContextDto getContext(Long id);

    TweetResponseDto postTweet(TweetRequestDto tweetRequest);
    
    List<HashtagResponseDto> getTagsByTweetId(Long tweetId);

}
