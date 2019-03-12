package com.excilys.cdb.model;

public class Company {
private Long id ;
private String name ;

public Company() {
	super();
	// TODO Auto-generated constructor stub
}

public Company(Long id, String name) {
	super();
	this.id = id;
	this.name = name;
}
public Company( String name) {
	super();
	this.name = name;
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
@Override
public String toString() {
	return "Company [id=" + id + ", name=" + name + "]";
}

}
