package com.excilys.cdb.controller;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.excilys.cdb.DAOImpl.ComputerDAOImpl;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.LogConfigurator;
import com.excilys.cdb.model.Page;

public class ComputerController {

	private Logger logger;
	private String stringRetour;

	/**
	 * This method print all computer on the database
	 * 
	 * @return String list of all Computer
	 */
	public String showListComputer() {
		logger = LogConfigurator.configureLoggerIfNull(logger, ComputerController.class.getName());
		stringRetour = "";
		ComputerDAOImpl daoComputer = new ComputerDAOImpl();
		List<Computer> listeComputer = new ArrayList<Computer>();
		listeComputer = daoComputer.getList();
		for (Computer c : listeComputer) {
			stringRetour += c.toString() + "\n";
		}
		return stringRetour;
	}
	/**
	 * This method print all computer on the database
	 * 
	 * @return String list of all Computer
	 */
	public String returnListComputer(List<Computer> listeComputer) {
		logger = LogConfigurator.configureLoggerIfNull(logger, ComputerController.class.getName());
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
		logger = LogConfigurator.configureLoggerIfNull(logger, ComputerController.class.getName());
		stringRetour = "";
		String secondeEnter = "";
		ComputerDAOImpl daoComputer = new ComputerDAOImpl();
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
		logger = LogConfigurator.configureLoggerIfNull(logger, ComputerController.class.getName());
		ComputerDAOImpl daoComputer = new ComputerDAOImpl();
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
		logger = LogConfigurator.configureLoggerIfNull(logger, ComputerController.class.getName());
		ComputerDAOImpl daoComputer = new ComputerDAOImpl();
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
		logger = LogConfigurator.configureLoggerIfNull(logger, ComputerController.class.getName());
		stringRetour = "";
		String forthEnter = "";
		ComputerDAOImpl daoComputer = new ComputerDAOImpl();
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
		logger = LogConfigurator.configureLoggerIfNull(logger, ComputerController.class.getName());
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

	public void pagination(Page page) {

		String stop ="N" ;
		int indice = page.getIndice();
		int numberOfComputer = page.getNumberOfComputer();
		ComputerDAOImpl daoComputer = new ComputerDAOImpl();
		List<Computer> listeComputer = new ArrayList<Computer>();
		listeComputer = daoComputer.getList();

		List<List<Computer>> listePagination = new ArrayList<List<Computer>>();

		logger = LogConfigurator.configureLoggerIfNull(logger, ComputerController.class.getName());
		stringRetour = "";

//		System.out.println("Choose number of page for the visualiation between(10,50,and 100);");
//
//		String stringNumber = lireEntrerClavier();

		try {
//			numberOfComputer = Integer.parseInt(stringNumber);
//			numberOfComputer =100 ;
//			int paginationMax = listeComputer.size()/2/ numberOfComputer;
//			if (numberOfComputer == 10 || numberOfComputer == 50 || numberOfComputer == 100) {
//				for (int i = 0; i < 1; i = i++) {
//					int fromIndex = i * numberOfComputer;
//					int toIndex = fromIndex + numberOfComputer;
//					listePagination.add(listeComputer.subList(fromIndex, toIndex));
//				}
//				logger.info(stringRetour);
//			}
				listePagination.add(listeComputer.subList(0, 100));
				System.out.println(listeComputer.subList(0, 100));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			stringRetour = "deleteComputerInDatabase : Number Format Exception \n";
			logger.error(stringRetour);
			
		}
			page.setNumberOfComputer(numberOfComputer);
		
		while (stop.equals("N")) {
			String listeStringComputer = returnListComputer(listePagination.get(indice));
			System.out.println(listePagination.get(indice));
			String sortie = listeStringComputer +"\n<-- ";
			for(int i = indice ;i < indice+5 ;i++) {
				sortie+=String.valueOf(i) +" "+"|";
			}
			sortie+= "--> \n Choose a index or presse \"Y\" to quite ";
			System.out.println(sortie);
			String nextStringIndice = lireEntrerClavier() ;
			
			try {
				indice = Integer.parseInt(nextStringIndice);
				
			} catch (NumberFormatException e) {
				e.printStackTrace();
				stringRetour = "deleteComputerInDatabase : Number Format Exception \n";
				logger.error(stringRetour);
				
			}
				page.setIndice(indice);
			
		}
	}

}
