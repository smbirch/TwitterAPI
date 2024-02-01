package com.cooksys.socialmedia.services;

import java.util.List;

import com.cooksys.socialmedia.dtos.TweetResponseDto;
import com.cooksys.socialmedia.dtos.UserResponseDto;

//import javassist.NotFoundException;

public interface TweetService {

List<TweetResponseDto> getAllTweets();

TweetResponseDto getTweetById(Long id);

List<TweetResponseDto> getRepostsById(Long id);

List<UserResponseDto> getLikesById(Long id);
}