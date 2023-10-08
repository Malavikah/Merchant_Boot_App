package org.jsp.merchantBootApp.exception;

public class InvalidCredentialsException extends RuntimeException{
	@Override
	public String getMessage() {
		return "Invalid id or phone or email";
	}
}
