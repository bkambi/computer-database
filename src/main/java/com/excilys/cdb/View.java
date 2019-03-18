package com.excilys.cdb;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.excilys.cdb.controler.CompanyController;
import com.excilys.cdb.controler.ComputerController;
import com.excilys.cdb.model.LogConfigurator;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.util.enume.Choix;
import com.excilys.cdb.util.enume.Instruction;

public class View {

	private static Logger logger;

	public static void main(String[] args) {

		LogConfigurator.addProperties();

		logger = Logger.getLogger(View.class.getName());

		Page page = new Page();
		String firstEnter = "";

		while (!firstEnter.equals("q")) {
			System.out.println(Instruction.HOME_INSTRUCTION.getDetailInstruction());
			Scanner sc = new Scanner(System.in);
			firstEnter = sc.nextLine();
			if (!firstEnter.equals("q") && isEntier(firstEnter)) {
				checkChoix(firstEnter);
			}

		}

	}

	public static void checkChoix(String choix) {
		int value = castToEntier(choix);
		CompanyController companyController = new CompanyController();
		ComputerController computerController = new ComputerController();
		switch (Choix.values()[value - 1]) {
		case LIST_COMPUTER:
			System.out.println(computerController.showListComputer());
			break;
		case LIST_COMPANY:
			System.out.println(companyController.showListCompany());
			break;
		case DETAILS_A_COMPUTER:
			System.out.println(Instruction.GET_ID_INSTRUCTION.getDetailInstruction());
			System.out.println(computerController.showDetailsOfThisComputer());
			break;
		case ADD_COMPUTER:
			System.out.println(Instruction.CREAT_INSTRUCTION.getDetailInstruction());
			computerController.addComputerToDatabase();
			break;
		case UPDATE_COMPUTER:
			System.out.println();
			break;
		case DELETE_COMPUTER:
			System.out.println(Instruction.GET_ID_INSTRUCTION.getDetailInstruction());
			System.out.println(computerController.deleteComputerInDatabase());
			break;
		default:
			;
		}
	}

	public static boolean isEntier(String entier) {
		try {
			Integer.parseInt(entier);
			return true;
		} catch (NumberFormatException e) {
			logger.error("Number Format Exception");
			e.printStackTrace();
			return false;
		}
	}

	public static int castToEntier(String entier) {
		try {
			return Integer.parseInt(entier);
		} catch (NumberFormatException e) {
			logger.error("Number Format Exception");
			e.printStackTrace();
			return 0;
		}
	}

}
