package com.cooksys.socialmedia.dtos;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HashtagResponseDto {
	
    private Long id;

    private String label;
    
    private Timestamp firstUsed;
    
    private Timestamp lastUsed;
}
