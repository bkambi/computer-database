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
	public static void addComputerService() {

	}

	public static List<String> getListIndice() {
		List<String> listIndiceRetour = new ArrayList<String>();
		int max = pageDashboard.getListeComputer().size() / pageDashboard.getNumberOfComputer();
		for (int i = pageDashboard.getIndice(); i> 0 & i < max & i+1<=i & i<=i-1; i++) {
			listIndiceRetour.add(String.valueOf(i));
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
			int maxRang = pageDashboard.getListeCompany().size() / pageDashboard.getNumberOfComputer();
			int minRang = 0;
			if ("next".equals(arrayIndice)) {
				indice = pageDashboard.getIndice() + 1 < maxRang ? pageDashboard.getIndice() + 1 : maxRang;
				numberOfComputer = numberOfComputerString != null ? Integer.parseInt(numberOfComputerString)
						: pageDashboard.getNumberOfComputer();
			} else if ("previous".equals(arrayIndice)) {
				indice = pageDashboard.getIndice() - 1 >= minRang ? pageDashboard.getIndice() - 1 : minRang;
				numberOfComputer = numberOfComputerString != null ? Integer.parseInt(numberOfComputerString)
						: pageDashboard.getNumberOfComputer();
			} else {
				try {
					indice = indiceString != null ? Integer.parseInt(indiceString) : pageDashboard.getIndice();
					numberOfComputer = numberOfComputerString != null ? Integer.parseInt(numberOfComputerString)
							: pageDashboard.getNumberOfComputer();
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
			}
			Page pageDashboardSession = (Page) session.getAttribute("pageDashboard");
			pageDashboardSession.setListeComputer(daoComputer.getList());
			pageDashboardSession.setListeCompany(daoCompany.getList());
			pageDashboardSession.setIndice(indice);
			pageDashboardSession.updateListComputerWithNewNumberOfComputer(numberOfComputer);
			session.setAttribute("pageDashboard", pageDashboardSession);
		}
	}
}
