package com.json;

public class IllegalArgument extends Exception {

	public IllegalArgument(String exc) {
		super(exc);
	}

	public String getMessage() {
		return super.getMessage();
	}
}
