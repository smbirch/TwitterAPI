package com.cooksys.socialmedia.controllers;

import com.cooksys.socialmedia.dtos.TweetResponseDto;
import com.cooksys.socialmedia.services.TweetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tweet")
public class TweetController {

	private final TweetService tweetService;

	/**
	 * Retrieves all non-deleted tweets in reverse-chronological order.
	 *
	 * @return An array of tweets in reverse-chronological order.
	 */
	@GetMapping
	  public List<TweetResponseDto> getAllTweets() {
		System.out.println("test");
	    return tweetService.getAllTweets();
	  }


}