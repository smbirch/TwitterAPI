package com.cooksys.socialmedia.services;

import java.util.List;

import com.cooksys.socialmedia.dtos.TweetResponseDto;

//import javassist.NotFoundException;

public interface TweetService {

List<TweetResponseDto> getAllTweets();

}
