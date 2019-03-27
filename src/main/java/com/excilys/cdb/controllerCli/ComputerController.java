package com.excilys.cdb.controllerCli;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.excilys.cdb.DAO.ComputerDAO;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;

public class ComputerController {

	private Logger logger = Logger.getLogger(ComputerController.class);
	private String stringRetour;
	private Page pageDashboard = new Page();

	/**
	 * This method print all computer on the database
	 * 
	 * @return String list of all Computer
	 */
	public void setListComputer() {
		
		stringRetour = "";
		ComputerDAO daoComputer = new ComputerDAO();
		pageDashboard.setListeComputer(daoComputer.getList());
		/*
		 * for (Computer c : listeComputer) { stringRetour += c.toString() + "\n"; }
		 * return stringRetour;
		 */
	}

	/**
	 * This method print all computer on the database
	 * 
	 * @return String list of all Computer
	 */
	public String returnListComputer(List<Computer> listeComputer) {
		stringRetour = "";
		for (Computer c : listeComputer) {
			stringRetour += c.toString() + "\n";
		}
		return stringRetour;
	}

	/**
	 * This method print a details of a specific computer on the database
	 * 
	 * @return String details of a specific computer
	 */
	public String showDetailsOfThisComputer() {
		stringRetour = "";
		String secondeEnter = "";
		ComputerDAO daoComputer = new ComputerDAO();
		secondeEnter = lireEntrerClavier();
		if (!secondeEnter.equals("q")) {
			try {
				Long id = Long.parseLong(secondeEnter);
				stringRetour += daoComputer.getById(id).toString() + "\n";
			} catch (NumberFormatException e) {
				e.printStackTrace();
				stringRetour += "showDetailsOfThisComputer : Number Format Exception";
				logger.error(stringRetour);

			}
		}
		return stringRetour;
	}

	/**
	 * Create a computer and add it to the database
	 */
	public void addComputerToDatabase() {
		ComputerDAO daoComputer = new ComputerDAO();
		Computer c = new Computer();
		String thirdEnter = "";
		thirdEnter = lireEntrerClavier();
		if (!thirdEnter.equals("q")) {
			String[] computerInfo = thirdEnter.split(";");
			if (computerInfo.length == 4) {
				try {

					String name = computerInfo[0];
					Timestamp introduced = castStringToTimestamp(computerInfo[1]);
					Timestamp discontinued = castStringToTimestamp(computerInfo[2]);
					Long companyId = Long.parseLong(computerInfo[3]);

					c.setName(name);
					c.setIntroduced(introduced);
					c.setDiscontinued(discontinued);
					c.setCompany_id(companyId);
					daoComputer.creat(c);
					System.out.println("creat ook !");
					logger.info("creat ook !");
				} catch (NumberFormatException e) {
					System.out.println("Format Company_id incorrect entrer un nombre");
					logger.error(stringRetour);
					e.printStackTrace();
				}
			} else {
				System.out.println("Remplir correctement tous les champs");
				logger.error("Remplir correctement tous les champs");
			}
		}
	}

	/**
	 * Update a computer in the database
	 */
	public void updateComputerInDatabase() {
		ComputerDAO daoComputer = new ComputerDAO();
		Computer c = new Computer();
		String thirdEnter = "";
		thirdEnter = lireEntrerClavier();
		if (!thirdEnter.equals("q")) {
			String[] computerInfo = thirdEnter.split(";");
			if (computerInfo.length == 4) {
				try {

					String name = computerInfo[0];
					Timestamp introduced = castStringToTimestamp(computerInfo[1]);
					Timestamp discontinued = castStringToTimestamp(computerInfo[2]);
					Long companyId = Long.parseLong(computerInfo[3]);

					c.setName(name);
					c.setIntroduced(introduced);
					c.setDiscontinued(discontinued);
					c.setCompany_id(companyId);
					daoComputer.update(c);
					System.out.println("Update ok ! ");
					logger.info("Update ok ! ");
				} catch (NumberFormatException e) {
					System.out.println("Format Company_id incorrect entrer un nombre");
					logger.error(stringRetour);
					e.printStackTrace();
				}
			} else {
				System.out.println("Remplir correctement tous les champs");
				logger.error("Remplir correctement tous les champs");
			}
		}
	}

	/**
	 * Delete a computer on the database
	 * 
	 * @return String , information of the method
	 */
	public String deleteComputerInDatabase() {
		stringRetour = "";
		String forthEnter = "";
		ComputerDAO daoComputer = new ComputerDAO();
		forthEnter = lireEntrerClavier();
		if (!forthEnter.equals("q")) {
			try {
				Long id = Long.parseLong(forthEnter);
				daoComputer.delete(id);
				stringRetour = "Computer delete \n";
				logger.info(stringRetour);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				stringRetour = "deleteComputerInDatabase : Number Format Exception \n";
				logger.error(stringRetour);
			}
		}
		return stringRetour;
	}

	/**
	 * Cast String time to Timestamp
	 * 
	 * @param dateString
	 * @return
	 */
	public Timestamp castStringToTimestamp(String dateString) {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = null;
		try {
			date = dateFormat.parse(dateString);
		} catch (ParseException e) {
			stringRetour = "Format de la date incorrect";
			logger.error(stringRetour);
			e.printStackTrace();
		}
		long time = date.getTime();
		return new Timestamp(time);
	}

	/**
	 * Read the enter keybord
	 * 
	 * @return
	 */
	public String lireEntrerClavier() {
		Scanner sc = new Scanner(System.in);
		return sc.nextLine();
	}

	public void showListeComputer() {

		ComputerDAO daoComputer = new ComputerDAO();
		pageDashboard.setListeComputer(daoComputer.getList());
		pageDashboard.setIndice(0);
		pageDashboard.updateListComputerWithNewNumberOfComputer(100);
		
		int nextIndice =0;
		String stop = "N";
		while (stop.equals("N")) {

			String listeStringComputer = "";
			for (Computer c : pageDashboard.getListeComputerToShow()) {
				listeStringComputer += c.toString() + "\n";
			}
			String sortie = listeStringComputer + "\n<-- ";
			int max = pageDashboard.getListeComputer().size() / pageDashboard.getNumberOfComputer();
			for (int i = pageDashboard.getIndice(); i < max; i++) {
				sortie += String.valueOf(i) + " " + "|";
			}
			sortie += "--> \n Choose a index or presse \"Y\" to quite ";
			System.out.println(sortie);
			String nextStringIndice = lireEntrerClavier();
			
			if (nextStringIndice.toUpperCase().equals("Y")) {
				break;
			}
			
			try {
				nextIndice = Integer.parseInt(nextStringIndice);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				stringRetour = "deleteComputerInDatabase : Number Format Exception \n";
				logger.error(stringRetour);
			}
			pageDashboard.updateListComputerWithNewIndice((nextIndice < max) ? nextIndice : 0);
		}
	}

}
