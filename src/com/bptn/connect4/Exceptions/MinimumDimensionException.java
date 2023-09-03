package com.bptn.connect4.Exceptions;

public class MinimumDimensionException extends Exception {

	private String message;
	
	public MinimumDimensionException(String message) {
		super(message);
		this.message=message;
		
	}

	public String getMessage() {
		return message;
	}
	
	
}
