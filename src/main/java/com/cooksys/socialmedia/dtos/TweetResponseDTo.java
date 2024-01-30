package com.cooksys.socialmedia.dtos;

import com.cooksys.socialmedia.entities.*;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class TweetResponseDTo {

  private Long id;

  private User Author;
  
  private String content;
  
  private Tweet inReplyTo;
  
  private Tweet repostOf;

}
