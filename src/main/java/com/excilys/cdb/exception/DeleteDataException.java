package com.excilys.cdb.exception;

public class DeleteDataException extends Exception {
	
	private final static String MESSAGE = "Delete the data was aborted ! ";

	public DeleteDataException() {
		super(MESSAGE);
	}
}
