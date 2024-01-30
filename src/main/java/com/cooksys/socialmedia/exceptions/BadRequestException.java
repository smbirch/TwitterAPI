package com.cooksys.socialmedia.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BadRequestException extends RuntimeException {

			
	/**
	 * 
	 */
	private static final long serialVersionUID = 5063884045769646275L;
	private String message;
}