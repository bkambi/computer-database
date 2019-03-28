package com.excilys.cdb.exception;

public class InvalidDataComputerException extends Exception {

	private final static String MESSAGE = "The data for the computer is invalid ! ";

	public InvalidDataComputerException() {
		super(MESSAGE);
	}
	
}
