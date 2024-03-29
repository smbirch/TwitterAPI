package com.cooksys.socialmedia.controllers;


import com.cooksys.socialmedia.dtos.HashtagResponseDto;
import com.cooksys.socialmedia.dtos.TweetResponseDto;
import com.cooksys.socialmedia.services.HashtagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tags")
public class HashtagController {

    private final HashtagService hashtagService;

    @GetMapping
    public List<HashtagResponseDto> retrieveAllTags() {
        return hashtagService.getAllTags();
    }

    @GetMapping("/{label}")
    public List<TweetResponseDto> retrieveTweetsBylabel(@PathVariable("label") String label) {
        return hashtagService.getTweetsByTag(label);
    }


}