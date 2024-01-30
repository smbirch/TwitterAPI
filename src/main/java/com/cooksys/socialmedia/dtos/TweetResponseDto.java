package com.cooksys.socialmedia.dtos;

import com.cooksys.socialmedia.entities.Tweet;
import com.cooksys.socialmedia.entities.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class TweetResponseDto {

    private Long id;

    private User Author;

    private String content;

    private Tweet inReplyTo;

    private Tweet repostOf;

}