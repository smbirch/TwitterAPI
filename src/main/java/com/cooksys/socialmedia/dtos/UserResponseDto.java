package com.cooksys.socialmedia.dtos;

import com.cooksys.socialmedia.entities.Profile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponseDto {

    private String username;

    private Timestamp joined;

    private Profile profile;

}