package com.hanains.mysite.exception;

public class RepositoryException extends RuntimeException {

	public RepositoryException() {
		super("Repository Exception");

	}

	public RepositoryException(String message) {
		super("Repository Exception :" + message);
		// TODO Auto-generated constructor stub
	}
	
	
}
