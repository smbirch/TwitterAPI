package com.cooksys.socialmedia.dtos;

import java.util.List;

import com.cooksys.socialmedia.entities.Tweet;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ContextDto {

	  private Tweet target;
	  	  
	  private List<Tweet> before;

	  private List<Tweet> after;
}
