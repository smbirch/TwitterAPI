package com.cooksys.socialmedia.dtos;

import com.cooksys.socialmedia.entities.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ProfileDto {
  
  private String firstName;
  
  private String lastName;
  
  private String email;
  
  private String phone;
}