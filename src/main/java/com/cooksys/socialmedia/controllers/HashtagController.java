package com.cooksys.socialmedia.controllers;



import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.socialmedia.dtos.HashtagResponseDto;
import com.cooksys.socialmedia.dtos.TweetResponseDto;
import com.cooksys.socialmedia.services.HashtagService;

import jakarta.persistence.Entity;


@RestController
@RequestMapping("/tags")
public class HashtagController {

    private final HashtagService hashtagService;

    public HashtagController(HashtagService hashtagService) {
        this.hashtagService = hashtagService;
    }

    @GetMapping
    public List<HashtagResponseDto> retrieveAllTags() {
    		return hashtagService.getAllTags();
    }

    @GetMapping("/{label}")
    public List<TweetResponseDto> retrieveTweetsBylabel(@PathVariable String label) {	
    		return hashtagService.getTweetsByTag(label);
    }


}
