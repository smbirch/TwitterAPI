package com.cooksys.socialmedia.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

//import java.util.List;

//import javassist.NotFoundException;

//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PatchMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.socialmedia.dtos.TweetResponseDTo;
import com.cooksys.socialmedia.dtos.TweetResponseDto;
import com.cooksys.socialmedia.services.TweetService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Tweet")
public class TweetController {

	private final TweetService tweetService;
	
	  @GetMapping
	  public List<TweetResponseDto> getAllTweets() {
	    return tweetService.getAllTweets();
	  }


}