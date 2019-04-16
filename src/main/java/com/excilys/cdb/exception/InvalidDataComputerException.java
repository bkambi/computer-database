package com.excilys.cdb.exception;

public class InvalidDataComputerException extends Exception {

	private final static String MESSAGE = "The data form the formular to creat a computer is invalid ! ";

	public InvalidDataComputerException() {
		super(MESSAGE);
	}
	
}
