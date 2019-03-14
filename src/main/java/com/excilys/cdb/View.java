package com.excilys.cdb;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import com.excilys.cdb.DAOImpl.CompanyDAOImpl;
import com.excilys.cdb.DAOImpl.ComputerDAOImpl;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;

public class View {

	public static void main(String[] args) {	
		String firstEnter ="";
		String instruction = " Tapez un numéro ou quittez (q)! \n" + 
				" =============================================== \n" + 
				" 1- List computers\n" + 
				" 2- List companies\n" + 
				" 3- Show computer details (the detailed information of only one computer)\n" + 
				" 4- Create a computer\n" + 
				" 5- Update a computer\n" + 
				" 6- Delete a computer\n" + 
				" =============================================== \n";
		
		while(!firstEnter.equals("q")) {
			System.out.println(instruction);
			Scanner sc = new Scanner(System.in);
			firstEnter = sc.nextLine() ;
			
			switch (firstEnter) {
			case "1": 
				showListComputer();
				break;
			case "2": 
				showListCompany();
				break;
			case "3": 
				showDetailsOfThisComputer();
				break;
			case "4": 
				addComputerToDatabase();
				break;
			case "5": 
				break;
			case "6": 
				deleteComputerInDatabase();
				break;
			} 
		}
		
	}
	
	public static void showListComputer() {
		ComputerDAOImpl daoComputer = new ComputerDAOImpl();
		List<Computer> listeComputer = new ArrayList<Computer>();
		listeComputer = daoComputer.getList() ;
		for (Computer c : listeComputer) {
			System.out.println(c);
			
		}
		System.out.println("\n");
	}
	public static void showListCompany(){
		CompanyDAOImpl daoCompany = new CompanyDAOImpl();
		List<Company> listeCompany = new ArrayList<Company>();
		for (Company c : listeCompany) {
			System.out.println(c);
			
		}
		System.out.println("\n");
	}
	public static void showDetailsOfThisComputer() {
		String secondeEnter ="";
		ComputerDAOImpl daoComputer = new ComputerDAOImpl();
		while(!secondeEnter.equals("q")) {
			System.out.println("Entrer un id ou quitter(q): \n " + 
					" =============================================== \n");
			Scanner sc = new Scanner(System.in);
			secondeEnter = sc.nextLine();
			if( !secondeEnter.equals("q")){
				try{
					Long id = Long.parseLong(secondeEnter);
					System.out.println(daoComputer.getById(id));
					}catch(NumberFormatException e) {
						e.printStackTrace();
						System.out.println("showDetailsOfThisComputer : Number Format Exception");
					}
			}
			System.out.println("\n");
		}
	}
	public static void addComputerToDatabase() {
		ComputerDAOImpl daoComputer = new ComputerDAOImpl();
		Computer c=new Computer();
		String thirdEnter ="";
		while(!thirdEnter.equals("q")) {
			System.out.println("Enter information withe ';' like : \n"+
					"name;JJ/MM/AAAA;JJ/MM/AAAA;company_id \n"+
					"example : Apple;22/12/1996;22/12/2012;44 \n"+
					"ou quitter(q): \n" + 
					"=============================================== \n");
			Scanner sc = new Scanner(System.in);
			
			thirdEnter = sc.nextLine();
			if( !thirdEnter.equals("q")){
				String[]computerInfo = thirdEnter.split(";");
				if(computerInfo.length == 4 ) {
					
					try {
						
						String name = computerInfo[0];
						Timestamp introduced = castStringToTimestamp(computerInfo[1]);
						Timestamp discontinued =castStringToTimestamp(computerInfo[2]);
						Long companyId = Long.parseLong(computerInfo[3]);
						
						c.setName(name);
						c.setIntroduced(introduced);
						c.setDiscontinued(discontinued);
						c.setCompany_id(companyId);
						daoComputer.creat(c);
						System.out.println("Computer créer");
					}catch(NumberFormatException e){
						System.out.println("Format Company_id incorrect entrer un nombre");
						e.printStackTrace();
					}
				}else {
					System.out.println("Remplir correctement tous les champs");
				}
				
			}
		}
	}
	public static void updateComputerInDatabase() {
		
	}
	public static void deleteComputerInDatabase() {
		String forthEnter ="";
		ComputerDAOImpl daoComputer = new ComputerDAOImpl();
		while(!forthEnter.equals("q")) {
			System.out.println("Entrer un id ou quitter(q): \n " + 
					" =============================================== \n");
			Scanner sc = new Scanner(System.in);
			forthEnter = sc.nextLine();
			if( !forthEnter.equals("q")){
				try{
					Long id = Long.parseLong(forthEnter);
					System.out.println(daoComputer.delete(id));
					}catch(NumberFormatException e) {
						e.printStackTrace();
						System.out.println("deleteComputerInDatabase : Number Format Exception");
					}
			}
			System.out.println("\n");
		}
	}
	
	public static Timestamp castStringToTimestamp(String dateString) {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = null;
		try {
			date = dateFormat.parse(dateString);
		} catch (ParseException e) {
			System.out.println("Format de la date incorrect");
			e.printStackTrace();
		}
		long time = date.getTime();
		return new Timestamp(time);
	}
	}
