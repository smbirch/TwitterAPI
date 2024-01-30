package com.cooksys.socialmedia.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.cooksys.socialmedia.dtos.TweetRequestDto;
import com.cooksys.socialmedia.dtos.TweetResponseDTo;
import com.cooksys.socialmedia.entities.Tweet;
import com.cooksys.socialmedia.mappers.TweetMapper;
import com.cooksys.socialmedia.repositories.TweetRepository;
import com.cooksys.socialmedia.services.TweetService;

//import javassist.NotFoundException;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TweetServiceImpl implements TweetService {

  private final TweetRepository tweetRepository;
  private final TweetMapper tweetMapper;

  @Override
  public List<TweetResponseDTo> getAllTweets() {
    return tweetMapper.entitiesToDtos(tweetRepository.findAll());
  }

}
