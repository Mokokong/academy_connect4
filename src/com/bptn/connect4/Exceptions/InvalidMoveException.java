package com.bptn.connect4.Exceptions;

public class InvalidMoveException extends Exception {
	
	private String message;

	public InvalidMoveException(String message) {
		super(message);
		this.message=message;
	}
	
	public String getMessage() {
		return message;
	}
	
}
