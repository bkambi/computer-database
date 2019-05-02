package com.excilys.cdb.util.enume;

public enum Instruction {
	HOME_INSTRUCTION(" Enter an number from below or exit (q):! \n" + 
			" =============================================== \n" + 
			" 1- List computers\n" + 
			" 2- List companies\n" + 
			" 3- Show computer details (the detailed information of only one computer)\n" + 
			" 4- Create a computer\n" + 
			" 5- Update a computer\n" + 
			" 6- Delete a computer\n" + 
			" =============================================== \n") , 
	CREAT_INSTRUCTION("Enter information with ';' like : \n"+
			"name;JJ/MM/AAAA;JJ/MM/AAAA;company_id \n"+
			"example : Apple;22/12/1996;22/12/2012;44 \n"+
			"or exit (q): \n" + 
			"=============================================== \n"), 
	GET_ID_INSTRUCTION("Enter an id or exit (q): \n " + 
					" =============================================== \n"),
	DELETE_INSTRUCTION(""),
	UPDATE_INSTRUCTION("");
	
	private String detailInstruction ="";
	Instruction(String detailInstruction) {
		this.detailInstruction = detailInstruction;
	}
	public String getDetailInstruction() {
		return detailInstruction;
	}
	public void setDetailInstruction(String detailInstruction) {
		this.detailInstruction = detailInstruction;
	}
	
	
}
