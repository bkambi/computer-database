package com.excylis.cdb.console.controller;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.excilys.cdb.util.enume.Instruction;
import com.excilys.cdb.util.enume.Choix;

public class LauncherCli {

	private static Logger logger;

	public static void main(String[] args) {
		logger = Logger.getLogger(LauncherCli.class.getName());

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
		Long value = castToEntier(choix);
		ComputerCliController computerController = new ComputerCliController();
		CompanyCliController companyController = new CompanyCliController();
		switch (Choix.values()[(int) (value - 1)]) {
		case LIST_COMPUTER:
			System.out.println(computerController.retrieveAllComputers());
			break;
		case LIST_COMPANY:
			System.out.println(companyController.retrieveAllCompany());
			break;
		case DETAILS_A_COMPUTER:
			System.out.println(Instruction.GET_ID_INSTRUCTION.getDetailInstruction());
			Scanner sc = new Scanner(System.in);
			String firstEnter = sc.nextLine();
			if (isEntier(firstEnter)) {
				System.out.println(computerController.retrieveOneComputer(castToEntier(firstEnter)));
			}

			break;
		case ADD_COMPUTER:
			System.out.println(Instruction.CREAT_INSTRUCTION.getDetailInstruction());
			computerController.addComputer();
			break;
		case UPDATE_COMPUTER:
			System.out.println(Instruction.UPDATE_INSTRUCTION.getDetailInstruction());
			Scanner sc2 = new Scanner(System.in);
			String id = sc2.nextLine();
			if (isEntier(id)) {
				computerController.updateComputer(castToEntier(id));
			}
			
			break;
		case DELETE_COMPUTER:
			System.out.println(Instruction.GET_ID_INSTRUCTION.getDetailInstruction());
			System.out.println(computerController.deleteComputer());
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

	public static Long castToEntier(String entier) {
		try {
			return Long.valueOf(entier);
		} catch (NumberFormatException e) {
			logger.error("Number Format Exception");
			e.printStackTrace();
			return 0L;
		}
	}

}
