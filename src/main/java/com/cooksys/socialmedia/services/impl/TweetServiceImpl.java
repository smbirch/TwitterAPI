package com.cooksys.socialmedia.services.impl;

import com.cooksys.socialmedia.dtos.TweetResponseDto;
import com.cooksys.socialmedia.entities.Tweet;
import com.cooksys.socialmedia.mappers.TweetMapper;
import com.cooksys.socialmedia.repositories.TweetRepository;
import com.cooksys.socialmedia.services.TweetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TweetServiceImpl implements TweetService {

  private final TweetRepository tweetRepository;
  private final TweetMapper tweetMapper;

  @Override
  public List<TweetResponseDto> getAllTweets() {
    // Fetch only non-deleted tweets in reverse chronological order
    List<Tweet> nonDeletedTweets = tweetRepository.findByDeletedFalseOrderByPostedDesc();

    // Map the non-deleted tweets to DTOs
    return tweetMapper.entitiesToDtos(nonDeletedTweets);  }

}