package com.excilys.cdb.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.excilys.cdb.util.enume.Instruction;

public class Page {

	private String title;
	private String header;
	private String Body;
	private String footer;
	private int indice;
	private int numberOfComputer;
	private List<Company> listeCompany;
	private List<Computer> listeComputer;
	private List<Computer> listeComputerToShow;

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

	public String getBody() {
		return Body;
	}

	public void setBody(String body) {
		Body = body;
	}

	public String getFooter() {
		return footer;
	}

	public void setFooter(String footer) {
		this.footer = footer;
	}

	public int getIndice() {
		return indice;
	}

	public void setIndice(int indice) {
		this.indice = indice;
	}

	public int getNumberOfComputer() {
		return numberOfComputer;
	}

	public void setNumberOfComputer(int numberOfComputer) {
		this.numberOfComputer = numberOfComputer;
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

	public List<Computer> getListeComputerToShow() {
		return listeComputerToShow;
	}

	public void setListeComputerToShow(List<Computer> listeComputerToShow) {
		this.listeComputerToShow = listeComputerToShow;
	}

	@Override
	public String toString() {
		return "Page [title=" + title + ", header=" + header + ", footer=" + footer + ", listeCompany=" + listeCompany
				+ ", listeComputer=" + listeComputer + "]";
	}

	public void updateListComputerWithNewIndice(int indice) {
		this.setIndice(indice);
		if (listeComputer.size() != 0) {
			int fromIndex = (indice * numberOfComputer) <= listeComputer.size() ? (indice * numberOfComputer)
					: listeComputer.size() - numberOfComputer;
			int toIndex = (fromIndex + numberOfComputer) <= listeComputer.size() ? (fromIndex + numberOfComputer)
					: listeComputer.size();
			if (fromIndex >= 0 & toIndex >= 0)
				this.setListeComputerToShow(listeComputer.subList(fromIndex, toIndex));
		} else {
			this.setListeComputerToShow(new ArrayList<Computer>());
		}
	}

	public void updateListComputerWithNewNumberOfComputer(int numberOfComputer) {
		this.setNumberOfComputer(numberOfComputer);
		if (listeComputer.size() != 0) {
			int fromIndex = (indice * numberOfComputer) <= listeComputer.size() ? (indice * numberOfComputer)
					: listeComputer.size() - numberOfComputer;
			int toIndex = (fromIndex + numberOfComputer) <= listeComputer.size() ? (fromIndex + numberOfComputer)
					: listeComputer.size();
			if (fromIndex >= 0 & toIndex >= 0)
				this.setListeComputerToShow(listeComputer.subList(fromIndex, toIndex));
		} else {
			this.setListeComputerToShow(new ArrayList<Computer>());
		}

	}

}
