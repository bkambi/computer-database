package com.excilys.cdb.model;

import java.util.List;

public class Page {
	
	private String title ;
	private String header ;
	private String footer ;
	private List<Company> listeCompany ;
	private List<Computer> listeComputer;
	
	public Page() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public String getFooter() {
		return footer;
	}
	public void setFooter(String footer) {
		this.footer = footer;
	}
	public List<Company> getListeCompany() {
		return listeCompany;
	}
	public void setListeCompany(List<Company> listeCompany) {
		this.listeCompany = listeCompany;
	}
	public List<Computer> getListeComputer() {
		return listeComputer;
	}
	public void setListeComputer(List<Computer> listeComputer) {
		this.listeComputer = listeComputer;
	}

	@Override
	public String toString() {
		return "Page [title=" + title + ", header=" + header + ", footer=" + footer + ", listeCompany=" + listeCompany
				+ ", listeComputer=" + listeComputer + "]";
	}
	
	
}
