package com.excilys.cdb.util.enume;

public enum Choix {
	LIST_COMPUTER("1"),LIST_COMPANY("2"),DETAILS_A_COMPUTER("3"),
	ADD_COMPUTER("4"),UPDATE_COMPUTER("5"),DELETE_COMPUTER("6");
	
	private String choix = "";
	
	Choix (String choix) {
		this.choix= choix;
	}
	public String getChoix() {
		return choix;
	}
	public void setChoix(String choix) {
		this.choix = choix;
	}

	};