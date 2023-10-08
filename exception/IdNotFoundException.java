package org.jsp.merchantBootApp.exception;

public class IdNotFoundException extends RuntimeException{
	@Override
	public String getMessage() {
		return "Invalid Merchant Id";
	}
}
