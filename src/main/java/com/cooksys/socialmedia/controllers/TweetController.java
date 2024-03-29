package com.cooksys.socialmedia.controllers;

import com.cooksys.socialmedia.dtos.*;
import com.cooksys.socialmedia.entities.Credentials;
import com.cooksys.socialmedia.exceptions.NotAuthorizedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.socialmedia.services.HashtagService;

import com.cooksys.socialmedia.services.TweetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tweets")
public class TweetController {

    private final TweetService tweetService;
    private final HashtagService hashtagService;


    /**
     * Retrieves all non-deleted tweets in reverse-chronological order.
     *
     * @return An array of tweets in reverse-chronological order.
     */
    @GetMapping
    public List<TweetResponseDto> getAllTweets() {
        return tweetService.getAllTweets();
    }

    /**
     * Retrieves the users mentioned in the tweet with the given id.
     * If that tweet is deleted or otherwise doesn't exist, an error should be sent in lieu of a response.
     * Deleted users should be excluded from the response.
     * IMPORTANT: Remember that tags and mentions must be parsed by the server!
     *
     * @return An array of 'User' objects representing the users mentioned in the tweet.
     */
    @GetMapping("/{id}/mentions")
    public List<UserResponseDto> getUsersMentionedByTweetId(@PathVariable("id") Long id) {
        return tweetService.getUsersMentionedByTweetId(id);
    }

    /**
     * Deletes the tweet with the specified ID. If no such tweet exists or the provided credentials
     * do not match the author of the tweet, an error should be sent in lieu of a response. If a tweet
     * is successfully "deleted", the response should contain the tweet data prior to deletion.
     * <p>
     * IMPORTANT: This action should not actually drop any records from the database! Instead, develop
     * a way to keep track of "deleted" tweets so that even if a tweet is deleted, data with
     * relationships to it (like replies and reposts) are still intact.
     *
     * @param id          The unique identifier of the tweet to be deleted.
     * @param credentials The credentials of the user attempting to delete the tweet.
     * @return A response containing the tweet data prior to deletion if the tweet is successfully
     * deleted; otherwise, an error response.
     * @throws IllegalArgumentException If the provided tweet ID is invalid.
     * @throws NotAuthorizedException   If the provided credentials do not match the author of the tweet.
     */
    @DeleteMapping("/{id}")
    public TweetResponseDto deleteTweet(@PathVariable("id") Long id, @RequestBody CredentialsDto credentials) {
        return tweetService.deleteTweetById(id, credentials);
    }
    @GetMapping("/{id}")
    public TweetResponseDto getTweetById(@PathVariable("id") Long id) {
        return tweetService.getTweetById(id);

    }

    @GetMapping("/{id}/reposts")
    public List<TweetResponseDto> getRepostsById(@PathVariable("id") Long id) {
        return tweetService.getRepostsById(id);
    }

    @GetMapping("/{id}/likes")
    public List<UserResponseDto> getLikesById(@PathVariable("id") Long id) {
        return tweetService.getLikesById(id);
    }

    @PostMapping("/{id}/reply")
    public TweetResponseDto createReply(@PathVariable("id") Long id, @RequestBody TweetRequestDto tweetRequest) {
        return tweetService.createReply(id, tweetRequest);
    }

    @PostMapping("/{id}/repost")
    public TweetResponseDto createRepost(@PathVariable("id") Long id, @RequestBody CredentialsDto credentials) {
        return tweetService.createRepost(id, credentials);
    }

    @GetMapping("/{id}/context")
    public ContextDto getContext(@PathVariable("id") Long id) {
        return tweetService.getContext(id);
    }
	
	@PostMapping
	public TweetResponseDto postTweet(@RequestBody TweetRequestDto tweetRequest) {
		return tweetService.postTweet(tweetRequest);
	}
	@GetMapping("{id}/tags")
	public List<HashtagResponseDto> retrieveTagsById(@PathVariable("id") Long tweetId) {
		return tweetService.getTagsByTweetId(tweetId);
	}
	
	@GetMapping("{id}/replies")
	public List<TweetResponseDto> getRepliesOfTweets(@PathVariable("id") Long id) {
		return tweetService.getTweetReplies(id);
	}
	
	@PostMapping("/{id}/like")
	public void postLike(@PathVariable("id") Long id, @RequestBody Credentials credentialsDto) {
		tweetService.postTweetLike(id, credentialsDto);
}
	
}

