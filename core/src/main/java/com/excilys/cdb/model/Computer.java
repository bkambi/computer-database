package com.excilys.cdb.model;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "computer")
public class Computer {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long id ;
	@Column(name = "name")
	private String name ;
	@Column(name = "introduced")
	private Timestamp introduced ;
	@Column(name = "discontinued")
	private Timestamp discontinued;
	@Column(name = "company_id")
	private Long companyId ;
	
	@ManyToOne
	@JoinColumn(name = "company_id",referencedColumnName="id",insertable = false, updatable = false)
	private Company company ;
	
	public Computer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Computer(String name, Timestamp introduced,Timestamp discontinued, Long companyId) {
		super();
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.companyId = companyId;
	}
	public Computer(Long id, String name, Timestamp introduced,Timestamp discontinued, Long companyId) {
		super();
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.companyId = companyId;
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
	
	public Timestamp getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(Timestamp discontinued) {
		this.discontinued = discontinued;
	}

	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	@Override
	public String toString() {
		return "[id=" + id + ", name=" + name + ", introduced=" + introduced + ", discontinued=" + discontinued
				+ ", companyId=" + companyId + "]";
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	
}
