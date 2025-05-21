package com.springcore.main.exception;

public class InvalidIdException extends RuntimeException {
	public InvalidIdException(String message) {
		super(message);
	}
}
