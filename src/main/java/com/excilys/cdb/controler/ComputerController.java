package com.excilys.cdb.controler;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.excilys.cdb.DAOImpl.ComputerDAOImpl;
import com.excilys.cdb.model.Computer;

public class ComputerController {

	private String stringRetour;

	/**
	 * This method print all computer on the database
	 * 
	 * @return String list of all Computer
	 */
	public String showListComputer() {
		stringRetour = "";
		ComputerDAOImpl daoComputer = new ComputerDAOImpl();
		List<Computer> listeComputer = new ArrayList<Computer>();
		listeComputer = daoComputer.getList();
		for (Computer c : listeComputer) {
			stringRetour += c.toString() +"\n";
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
		ComputerDAOImpl daoComputer = new ComputerDAOImpl();
		secondeEnter = lireEntrerClavier();
		if (!secondeEnter.equals("q")) {
			try {
				Long id = Long.parseLong(secondeEnter);
				stringRetour += daoComputer.getById(id).toString() + "\n";
			} catch (NumberFormatException e) {
				e.printStackTrace();
				stringRetour += "showDetailsOfThisComputer : Number Format Exception";
			}
		}
		return stringRetour;
	}

	/**
	 * Create a computer and add it to the database
	 */
	public void addComputerToDatabase() {
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
					System.out.println("Computer créer");
				} catch (NumberFormatException e) {
					System.out.println("Format Company_id incorrect entrer un nombre");
					e.printStackTrace();
				}
			} else {
				System.out.println("Remplir correctement tous les champs");
			}
		}
	}

	/**
	 * 
	 */
	public void updateComputerInDatabase() {

	}

	/**
	 * Delete a computer on the database
	 * 
	 * @return String , information of the method
	 */
	public String deleteComputerInDatabase() {
		stringRetour = "";
		String forthEnter = "";
		ComputerDAOImpl daoComputer = new ComputerDAOImpl();
		forthEnter = lireEntrerClavier();
		if (!forthEnter.equals("q")) {
			try {
				Long id = Long.parseLong(forthEnter);
				daoComputer.delete(id);
				stringRetour = "Computer delete \n";
			} catch (NumberFormatException e) {
				e.printStackTrace();
				stringRetour = "deleteComputerInDatabase : Number Format Exception \n";
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

}
