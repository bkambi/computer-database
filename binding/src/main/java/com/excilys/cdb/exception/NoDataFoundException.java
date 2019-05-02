package com.excilys.cdb.exception;

public class NoDataFoundException extends Exception {
	private final static String MESSAGE = "The are/is no data found for this method ! ";

	public NoDataFoundException() {
		super(MESSAGE);
	}
	
}
