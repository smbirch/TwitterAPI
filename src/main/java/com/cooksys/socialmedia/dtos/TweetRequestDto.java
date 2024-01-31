package com.cooksys.socialmedia.dtos;

import lombok.AllArgsConstructor;
//import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TweetRequestDto {
  
  private String content;
  
  private CredentialsDto credentials;

}