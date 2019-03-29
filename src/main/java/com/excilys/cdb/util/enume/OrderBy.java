package com.excilys.cdb.util.enume;

public enum OrderBy {
	
	COMPUTER_NAME("name"),COMPUTER_INTRODUCED("introduced") , COMPUTER_DISCONTINUED("discontinued"),COMPUTER_COMPANY("company");

	private String input ="" ; 
	
	OrderBy(String input){
		this.input = input ;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}
}
