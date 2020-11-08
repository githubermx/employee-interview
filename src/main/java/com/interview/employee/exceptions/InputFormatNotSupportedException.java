package com.interview.employee.exceptions;

public class InputFormatNotSupportedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InputFormatNotSupportedException(String message) {
		super(message);
	}

	public InputFormatNotSupportedException() {
		super();
		// TODO Auto-generated constructor stub
	}

}
