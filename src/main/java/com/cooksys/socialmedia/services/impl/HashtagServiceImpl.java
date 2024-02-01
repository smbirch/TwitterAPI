package com.cooksys.socialmedia.services.impl;

import com.cooksys.socialmedia.dtos.HashtagResponseDto;
import com.cooksys.socialmedia.dtos.TweetResponseDto;
import com.cooksys.socialmedia.entities.Hashtag;
import com.cooksys.socialmedia.entities.Tweet;
import com.cooksys.socialmedia.exceptions.NotFoundException;
import com.cooksys.socialmedia.mappers.HashtagMapper;
import com.cooksys.socialmedia.mappers.TweetMapper;
import com.cooksys.socialmedia.repositories.HashtagRepository;
import com.cooksys.socialmedia.repositories.TweetRepository;
import com.cooksys.socialmedia.services.HashtagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HashtagServiceImpl implements HashtagService {

    private final HashtagRepository hashtagRepository;
    private final HashtagMapper hashtagMapper;
    private final TweetRepository tweetRepository;
    private final TweetMapper tweetMapper;

    @Override
    public List<HashtagResponseDto> getAllTags() {
        return hashtagMapper.entitiesToDtos(hashtagRepository.findAll());
    }

    @Override
    public List<TweetResponseDto> getTweetsByTag(String label) {

        List<Hashtag> hashList = hashtagRepository.findAll();

        Hashtag current = new Hashtag();
        for (Hashtag h : hashList) {
            if (h.getLabel().equals("#" + label)) {
                System.out.println(h.getLabel());
                current = h;
            }
        }
        if (current.getTweets().isEmpty()) {
            throw new NotFoundException("Label could not be found for:" + label);
        }
        List<TweetResponseDto> tweets = new ArrayList<>();

        for (Tweet t : tweetRepository.findAll()) {
            if (t.getHashtags().contains(current) && !t.isDeleted()) {
                tweets.add(tweetMapper.entityToDto(t));
            }
        }
        Collections.sort(tweets, Comparator.comparing(TweetResponseDto::getPosted, Comparator.reverseOrder()));

        return tweets;
    }
}