package com.excilys.cdb.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;

import com.excilys.cdb.DAO.CompanyDAO;
import com.excilys.cdb.DAO.ComputerDAO;
import com.excilys.cdb.DTO.ComputerDTO;
import com.excilys.cdb.exception.DeleteDataException;
import com.excilys.cdb.exception.InvalidDataComputerException;
import com.excilys.cdb.mapper.ComputerMapper;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.util.Parser;
import com.excilys.cdb.util.enume.OrderBy;

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
		List<Computer> listComputer = pageDashboard.getListeComputerToShow();
		List<Company> listCompany = pageDashboard.getListeCompany();

		for (Computer computer : listComputer) {
			Optional<Company> optionalCompany = listCompany.stream()
					.filter(company -> company.getId() == computer.getCompany_id()).findFirst();
			String nameCompnay = optionalCompany.isPresent() ? optionalCompany.get().getName() : "";
			listComputerDTO.add(ComputerMapper.mapDTO(computer, nameCompnay));
		}
		return listComputerDTO;

	}

	/**
	 * Filter the list by name la liste visible
	 * 
	 * @param req
	 * @param listComputer
	 * @return
	 */
	public static List<ComputerDTO> getFilterListComputer(HttpServletRequest req, List<ComputerDTO> listComputer) {

		String searchParam = req.getParameter("search");
		List<ComputerDTO> filtList = new ArrayList<ComputerDTO>();
		if (searchParam != null) {
			filtList = listComputer.stream()
					.filter(computerDto -> computerDto.getName().matches("(?i).*" + searchParam + ".*")
							| computerDto.getCompany().matches("(?i).*" + searchParam + ".*"))
					.collect(Collectors.toList());
		}
		return filtList;
	}

	public static List<ComputerDTO> getOrderListComputer(HttpServletRequest req, List<ComputerDTO> listComputer) {

		String orderByPram = req.getParameter("orderBy");
		String reverseParam = req.getParameter("reversed");
		int value = Integer.valueOf(orderByPram);
		List<ComputerDTO> filtList = new ArrayList<ComputerDTO>();
		switch (OrderBy.values()[value - 1]) {
		case COMPUTER_NAME:
			filtList = "0".equals(reverseParam)
					? listComputer.stream().sorted(Comparator.comparing(ComputerDTO::getName))
							.collect(Collectors.toList())
					: listComputer.stream().sorted(Comparator.comparing(ComputerDTO::getName).reversed())
							.collect(Collectors.toList());
			break;
		case COMPUTER_INTRODUCED:
			filtList = "0".equals(reverseParam)
					? listComputer.stream().sorted(Comparator.comparing(ComputerDTO::getIntroduced))
							.collect(Collectors.toList())
					: listComputer.stream().sorted(Comparator.comparing(ComputerDTO::getIntroduced).reversed())
							.collect(Collectors.toList());
			break;
		case COMPUTER_DISCONTINUED:
			filtList = "0".equals(reverseParam)
					? listComputer.stream().sorted(Comparator.comparing(ComputerDTO::getDiscontinued))
							.collect(Collectors.toList())
					: listComputer.stream().sorted(Comparator.comparing(ComputerDTO::getDiscontinued).reversed())
							.collect(Collectors.toList());
			break;
		case COMPUTER_COMPANY:
			filtList = "0".equals(reverseParam)
					? listComputer.stream().sorted(Comparator.comparing(ComputerDTO::getCompany))
							.collect(Collectors.toList())
					: listComputer.stream().sorted(Comparator.comparing(ComputerDTO::getCompany).reversed())
							.collect(Collectors.toList());
			break;
		}
		return filtList;
	}

	/**
	 * Add a Computer in the data base using the method of the DAO
	 * 
	 * @throws InvalidDataComputerException
	 */

	public static void handleRequestForAddComputer(HttpServletRequest request, HttpServletResponse response)
			throws InvalidDataComputerException {

		Computer computer;
		Optional<Computer> optionalComputer = ComputerMapper.mapViewDtoToComputer(request);
		computer = optionalComputer.isPresent() ? optionalComputer.get() : new Computer();
		if (isValidatedByTheBack(computer)) {
			daoComputer.creat(computer);
		} else {
			throw new InvalidDataComputerException();
		}
	}

	/**
	 * Update a Computer in the data base using the method of the DAO
	 * 
	 * @throws InvalidDataComputerException
	 */

	public static void handleRequestForUpdateComputer(HttpServletRequest request, HttpServletResponse response)
			throws InvalidDataComputerException {
		System.out.println("computerName : " + request.getParameter("computerName") + " introduced : "
				+ request.getParameter("introduced") + " discontinued : " + request.getParameter("discontinued")
				+ " company : " + request.getParameter("company"));

		Computer computer;
		Optional<Computer> optionalComputer = ComputerMapper.mapViewDtoToComputer(request);
		computer = optionalComputer.isPresent() ? optionalComputer.get() : new Computer();
		if (isValidatedByTheBack(computer)) {
			daoComputer.update(computer);
		} else {
			throw new InvalidDataComputerException();
		}
	}
	
	/**
	 * Delete a Computer in the data base using the method of the DAO
	 * 
	 * @throws InvalidDataComputerException
	 */

	public static void handleRequestForDeleteComputer(HttpServletRequest request, HttpServletResponse response)
			throws DeleteDataException {
		String [] args = request.getParameter("selection").split(",");
		List<Long> listeComputerToDelete = new ArrayList<Long>();
		for(String s : args)
			listeComputerToDelete.add(Long.valueOf(s));
		
		try {
			daoComputer.delete(listeComputerToDelete);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DeleteDataException();
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
	 * Get the List of the indices for the pagination
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
				System.out.println("try");
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

	public static Optional<ComputerDTO> getComputerDTO(HttpServletRequest req) {

		ComputerDTO computerDto = null;

		String computerIdString = req.getParameter("computer");
		if (computerIdString != null) {
			try {
				Long computerID = Long.valueOf(computerIdString);
				Computer computer = daoComputer.getById(computerID);
				Optional<Company> optionalCompany = pageDashboard.getListeCompany().stream()
						.filter(company -> company.getId() == computer.getCompany_id()).findFirst();
				String nameCompnay = optionalCompany.isPresent() ? optionalCompany.get().getName() : "";
				computerDto = ComputerMapper.mapDTO(computer, nameCompnay);
			} catch (NumberFormatException e) {
			}
		}
		return Optional.ofNullable(computerDto);

	}

	public static String getTotalComputer() {
		return String.valueOf(pageDashboard.getListeComputer().size());
	}

}
