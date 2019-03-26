package com.excilys.cdb.services;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;

import com.excilys.cdb.DAOImpl.CompanyDAOImpl;
import com.excilys.cdb.DAOImpl.ComputerDAOImpl;
import com.excilys.cdb.DTO.ComputerDTO;
import com.excilys.cdb.mapper.ComputerMapper;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.util.Parser;

public class ComputerServices {

	private static ComputerDAOImpl daoComputer = new ComputerDAOImpl();
	private static CompanyDAOImpl daoCompany = new CompanyDAOImpl();
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

	private static void handleRequestForAddComputer(HttpServletRequest request) {
		String computerName = (String) request.getAttribute("computerName");
		String introduced = (String) request.getAttribute("computerName");
		String discontinued = (String) request.getAttribute("discontinued");
		String companyId = (String) request.getAttribute("companyId");

		Computer computer = new Computer();
		computer.setName(computerName);
		computer.setIntroduced(Parser.stringToTimestamp(introduced).get());
		computer.setDiscontinued(Parser.stringToTimestamp(discontinued).get());
		if (!"-1".equals(companyId)) {
			computer.setId(Long.parseLong(companyId));
		}

		daoComputer.creat(computer);
	}

	public static List<String> getListIndice() {
		List<String> listIndiceRetour = new ArrayList<String>();
		int max = pageDashboard.getListeComputer().size() / pageDashboard.getNumberOfComputer();

		if (pageDashboard.getIndice() - 1 >= 0 & pageDashboard.getIndice() + 1 <= max) {
			listIndiceRetour.add(String.valueOf(pageDashboard.getIndice() - 1));
			listIndiceRetour.add(String.valueOf(pageDashboard.getIndice()));
			listIndiceRetour.add(String.valueOf(pageDashboard.getIndice() + 1));
		} else if (pageDashboard.getIndice() - 1 < 0 & pageDashboard.getIndice() + 1 <= max) {
			listIndiceRetour.add(String.valueOf(pageDashboard.getIndice()));
			listIndiceRetour.add(String.valueOf(pageDashboard.getIndice() + 1));
		} else if (pageDashboard.getIndice() - 1 >= 0 & pageDashboard.getIndice() + 1 > max) {
			listIndiceRetour.add(String.valueOf(pageDashboard.getIndice() - 1));
			listIndiceRetour.add(String.valueOf(pageDashboard.getIndice()));
			;
		} else {
			listIndiceRetour.add("0");
			listIndiceRetour.add("1");
			listIndiceRetour.add("2");
		}
		return listIndiceRetour;
	}

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
				System.out.println("indice : " + indice + "numberOfComputer :" + numberOfComputer);
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
