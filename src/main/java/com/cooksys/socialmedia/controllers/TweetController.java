package com.cooksys.socialmedia.controllers;

import com.cooksys.socialmedia.dtos.TweetResponseDto;
import com.cooksys.socialmedia.services.TweetService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Tweet")
public class TweetController {

	private final TweetService tweetService;

	public TweetController(TweetService tweetService) {
		this.tweetService = tweetService;
	}

	@GetMapping
	  public List<TweetResponseDto> getAllTweets() {
	    return tweetService.getAllTweets();
	  }


}