package com.excilys.cdb.services;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;

import com.excilys.cdb.DAO.CompanyDAO;
import com.excilys.cdb.DAO.ComputerDAO;
import com.excilys.cdb.DTO.ComputerDTO;
import com.excilys.cdb.mapper.ComputerMapper;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.util.Parser;

public class ComputerServices {

	private static ComputerDAO daoComputer = new ComputerDAO();
	private static CompanyDAO daoCompany = new CompanyDAO();
	private static Page pageDashboard;

	/**
	 * Return a Computer DTO if is not null : using Optional and the method of the
	 * DAO
	 * 
	 * @return ComputerDTO , DTO useful for the view
	 */
	public static List<ComputerDTO> getListComputerToShowService(HttpServletRequest req) {

		pageDashboardInit(req);

		List<ComputerDTO> listComputerDTO = new ArrayList<ComputerDTO>();

		for (Computer computer : pageDashboard.getListeComputerToShow()) {
			Optional<Company> optionalCompany = pageDashboard.getListeCompany().stream()
					.filter(company -> company.getId() == computer.getCompany_id()).findFirst();
			String nameCompnay = optionalCompany.isPresent() ? optionalCompany.get().getName() : "";
			listComputerDTO.add(ComputerMapper.mapDTO(computer, nameCompnay));
		}
		return listComputerDTO;

	}

	/**
	 * Add a Computer in the data base using the method of the DAO
	 */

	public static void handleRequestForAddComputer(HttpServletRequest request, HttpServletResponse response) {

		String computerName = request.getParameter("computerName");
		String introduced = request.getParameter("introduced");
		String discontinued = request.getParameter("discontinued");
		String companyId = request.getParameter("companyId");

		Computer computer = new Computer();

		computer.setName(computerName);
		computer.setIntroduced(Parser.stringToTimestamp(introduced).get());
		computer.setDiscontinued(Parser.stringToTimestamp(discontinued).get());
		Long companyID = null;

		try {
			companyID = Long.parseLong(companyId);
			computer.setCompany_id(companyID);
			if (isValidatedByTheBack(computer)) {
				daoComputer.creat(computer);
			}else {
				//TODO throw new exception InvalidDataException 
			}
			;
		} catch (NumberFormatException numberFormatException) {
			numberFormatException.printStackTrace();
		}
	}

	/**
	 * Check if the computer have a correct information
	 * 
	 * @param computer
	 * @return boolean , true if is validate , false if is not
	 */
	public static boolean isValidatedByTheBack(Computer computer) {
		boolean results = false;
		if (computer.getIntroduced().before(computer.getDiscontinued())) {
			Optional<Company> optionalCompany = daoCompany.getById(computer.getCompany_id());
			if (optionalCompany.isPresent()) {
				results = true;
			}
		}
		return results;
	}

	/**
	 * Get the Liste of the indice for the pagination
	 * 
	 * @return
	 */
	public static List<String> getListIndice() {

		List<String> listIndiceRetour = new ArrayList<String>();

		int max = pageDashboard.getListeComputer().size() / pageDashboard.getNumberOfComputer();
		int indice = pageDashboard.getIndice();

		if (indice - 1 >= 0 & indice + 1 <= max) {
			listIndiceRetour = new ArrayList<>(
					List.of(String.valueOf(indice - 1), String.valueOf(indice), String.valueOf(indice + 1)));
		} else if (indice - 1 < 0 & indice + 1 <= max) {
			listIndiceRetour = new ArrayList<>(List.of(String.valueOf(indice), String.valueOf(indice + 1)));
		} else if (pageDashboard.getIndice() - 1 >= 0 & pageDashboard.getIndice() + 1 > max) {
			listIndiceRetour = new ArrayList<>(List.of(String.valueOf(indice - 1), String.valueOf(indice)));
		} else {
			listIndiceRetour = new ArrayList<>(List.of("0", "1", "2"));
		}
		return listIndiceRetour;
	}

	/**
	 * Instance the Dashboard page for the dashboard.jsp
	 * 
	 * @param req
	 */
	public static void pageDashboardInit(HttpServletRequest req) {
		HttpSession session = req.getSession();
		String indiceString = (String) req.getParameter("indice");
		String numberOfComputerString = (String) req.getParameter("numberOfComputer");
		String arrayIndice = (String) req.getParameter("arrayIndice");

		int indice = 0;
		int numberOfComputer = 10;

		if (session.getAttribute("pageDashboard") == null) {
			pageDashboard = new Page();
			pageDashboard.setListeComputer(daoComputer.getList());
			pageDashboard.setListeCompany(daoCompany.getList());
			pageDashboard.setIndice(indice);
			pageDashboard.updateListComputerWithNewNumberOfComputer(numberOfComputer);
			session.setAttribute("pageDashboard", pageDashboard);
		} else {
			try {
				int maxRang = pageDashboard.getListeComputer().size() / pageDashboard.getNumberOfComputer();
				int minRang = 0;
				if ("next".equals(arrayIndice)) {
					indice = maxRang;
				} else if ("previous".equals(arrayIndice)) {
					indice = minRang;
				} else {
					indice = indiceString != null ? Integer.parseInt(indiceString) : pageDashboard.getIndice();
				}
				numberOfComputer = numberOfComputerString != null ? Integer.parseInt(numberOfComputerString)
						: pageDashboard.getNumberOfComputer();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			Page pageDashboardSession = (Page) session.getAttribute("pageDashboard");
			pageDashboardSession.setListeComputer(daoComputer.getList());
			pageDashboardSession.setListeCompany(daoCompany.getList());
			if (numberOfComputerString != null) {
				pageDashboardSession.setIndice(0);
			} else if (indiceString != null) {
				pageDashboardSession.setIndice(indice);
			} else if (arrayIndice != null) {
				pageDashboardSession.setIndice(indice);
			}
			pageDashboardSession.updateListComputerWithNewNumberOfComputer(numberOfComputer);
			session.setAttribute("pageDashboard", pageDashboardSession);
		}

	}
}
