package com.excilys.cdb.model;

import java.sql.Timestamp;

public class Computer {
	
	private Long id ;
	private String name ;
	private Timestamp introduced ;
	private Long company_id ;
	
	
	public Computer(Long id, String name, Timestamp introduced, Long company_id) {
		super();
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.company_id = company_id;
	}
	
	public Computer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Timestamp getIntroduced() {
		return introduced;
	}
	public void setIntroduced(Timestamp introduced) {
		this.introduced = introduced;
	}
	public Long getCompany_id() {
		return company_id;
	}
	public void setCompany_id(Long company_id) {
		this.company_id = company_id;
	}
	@Override
	public String toString() {
		return "Computer [id=" + id + ", name=" + name + ", introduced=" + introduced + ", company_id=" + company_id
				+ "]";
	}

}