package com.cooksys.socialmedia.services;

import com.cooksys.socialmedia.dtos.CredentialsDto;
import com.cooksys.socialmedia.dtos.TweetResponseDto;

import java.util.List;

//import javassist.NotFoundException;

public interface TweetService {

    List<TweetResponseDto> getAllTweets();


    TweetResponseDto deleteTweetById(Long tweetId, CredentialsDto credentials);
}