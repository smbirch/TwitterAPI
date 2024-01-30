package com.cooksys.socialmedia.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class ErrorDto{
	
	private String message;

	public ErrorDto(String message) {
		this.message = message;
	}
}