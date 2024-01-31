package com.cooksys.socialmedia.services.impl;

import com.cooksys.socialmedia.dtos.CredentialsDto;
import com.cooksys.socialmedia.dtos.TweetResponseDto;
import com.cooksys.socialmedia.dtos.UserResponseDto;
import com.cooksys.socialmedia.entities.Tweet;
import com.cooksys.socialmedia.entities.User;
import com.cooksys.socialmedia.exceptions.NotAuthorizedException;
import com.cooksys.socialmedia.exceptions.NotFoundException;
import com.cooksys.socialmedia.mappers.TweetMapper;
import com.cooksys.socialmedia.mappers.UserMapper;
import com.cooksys.socialmedia.repositories.TweetRepository;
import com.cooksys.socialmedia.services.TweetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TweetServiceImpl implements TweetService {

    private final TweetRepository tweetRepository;
    private final TweetMapper tweetMapper;
    private final UserMapper userMapper;


    @Override
    public List<TweetResponseDto> getAllTweets() {
        // Get non-deleted tweets in reverse chronological order
        List<Tweet> nonDeletedTweets = tweetRepository.findByDeletedFalseOrderByPostedDesc();
        return tweetMapper.entitiesToDtos(nonDeletedTweets);
    }

    // TODO: reimplement this once GET tweets/{id} is created
    @Override
    public TweetResponseDto deleteTweetById(Long tweetId, CredentialsDto credentials) {
        Tweet tweet = tweetRepository.findById(tweetId).orElseThrow(() -> new IllegalArgumentException("Invalid tweet ID: " + tweetId));

        if (tweet.isDeleted()) {
            throw new NotFoundException("Tweet with ID: " + tweetId + " not found");
        }
        // Check if the user has permission to delete the tweet
        if (!tweet.getAuthor().getCredentials().getUsername().equals(credentials.getUsername())) {
            throw new NotAuthorizedException("Unauthorized to delete tweet with ID: " + tweetId);
        }

        tweet.setDeleted(true);
        tweetRepository.save(tweet);

        return tweetMapper.entityToDto(tweet);
    }


    // TODO: reimplement this once GET tweets/{id} is created
    @Override
    public List<UserResponseDto> getUsersMentionedByTweetId(Long tweetId) {
        Tweet tweet = tweetRepository.findById(tweetId).orElseThrow(() -> new IllegalArgumentException("Invalid tweet ID: " + tweetId));

        if (tweet.isDeleted()) {
            throw new NotFoundException("Tweet with ID: " + tweetId + " not found");
        }

        List<User> allMentionedUsers = tweet.getMentionedUsers();

        if (allMentionedUsers.isEmpty()) {
            throw new NotFoundException("No users mentioned in this tweet: " + tweetId);
        }
        ArrayList<User> mentionedUsersNotDeleted = new ArrayList<>();
        for (User user : allMentionedUsers) {
            if (user.isDeleted()) {
                continue;
            }
            mentionedUsersNotDeleted.add(user);
        }

        return userMapper.entitiesToDtos(mentionedUsersNotDeleted);
    }


}