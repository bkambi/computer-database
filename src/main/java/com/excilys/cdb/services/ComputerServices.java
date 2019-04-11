package com.excilys.cdb.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

@Component
public class ComputerServices {
 
	@Autowired
	private  ComputerDAO daoComputer ;
	@Autowired
	private  CompanyDAO daoCompany ;
	private  Page pageDashboard;
	
	public ComputerServices() {
	}
 
	/**
	 * Return a Computer DTO if is not null : using Optional and the method of the
	 * DAO
	 * 
	 * @return ComputerDTO , DTO useful for the view
	 */
	public List<ComputerDTO> getListComputerToShowService(HttpServletRequest req) {

		pageDashboardInit(req);
		
		List<Computer> listComputer= pageDashboard.getListeComputerToShow();
		List<ComputerDTO> listComputerDTO = new ArrayList<ComputerDTO>();
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
	 * Add a Computer in the data base using the method of the DAO
	 * 
	 * @throws InvalidDataComputerException
	 */

	public void handleRequestForAddComputer(HttpServletRequest request, HttpServletResponse response)
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

	public  void handleRequestForUpdateComputer(HttpServletRequest request, HttpServletResponse response)
			throws InvalidDataComputerException {
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

	public  void handleRequestForDeleteComputer(HttpServletRequest request, HttpServletResponse response)
			throws DeleteDataException {
		String[] args = request.getParameter("selection").split(",");
		List<Long> listeComputerToDelete = new ArrayList<Long>();
		for (String s : args)
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
	public boolean isValidatedByTheBack(Computer computer) {
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
	public  List<String> getListIndice(HttpServletRequest req) {
		HttpSession session = req.getSession();
		List<String> listIndiceRetour = new ArrayList<String>();
		pageDashboard = (Page) session.getAttribute("pageDashboard");
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
			listIndiceRetour = new ArrayList<>(List.of("0"));
		}
		return listIndiceRetour;
	}

	/**
	 * Instance the Dashboard page for the dashboard.jsp
	 * 
	 * @param req
	 */
	public  void pageDashboardInit(HttpServletRequest req) {
		HttpSession session = req.getSession();
		
		String indiceString = (String) req.getParameter("indice");
		String numberOfComputerString = (String) req.getParameter("numberOfComputer");
		String arrayIndice = (String) req.getParameter("arrayIndice");
		String searchParam = req.getParameter("search");
		
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
			pageDashboard = (Page) session.getAttribute("pageDashboard");
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
			if (searchParam != null) {
				List<Computer> listComputer = getFilterAllListComputer(searchParam);
				pageDashboard.setListeComputer(listComputer);
				session.setAttribute("pageDashboard", pageDashboard);
			}else {
				pageDashboard.setListeComputer(daoComputer.getList());
			}
			pageDashboard.setListeCompany(daoCompany.getList());
			if (numberOfComputerString != null) {
				pageDashboard.setIndice(0);
			} else if (indiceString != null) {
				pageDashboard.setIndice(indice);
			} else if (arrayIndice != null) {
				pageDashboard.setIndice(indice);
			}
			pageDashboard.updateListComputerWithNewNumberOfComputer(numberOfComputer);
			session.setAttribute("pageDashboard", pageDashboard);
		}
	}

	public  Optional<ComputerDTO> getComputerDTO(HttpServletRequest req) {

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

	public  String getTotalComputer() {
		return String.valueOf(pageDashboard.getListeComputer().size());

	}

	/**
	 * Filter the list by name la liste visible
	 * 
	 * @param req
	 * @param listComputer
	 * @return
	 */
	public  List<Computer> getFilterAllListComputer(String searchParam ) {


		List<Computer> listAllComputer = daoComputer.getList();
		List<Computer> allListFilter = new ArrayList<Computer>();

		List<Company> listCompany = pageDashboard.getListeCompany();

		List<ComputerDTO> filterList = new ArrayList<ComputerDTO>();
		List<ComputerDTO> listAllComputerDto = new ArrayList<ComputerDTO>();

		
		for (Computer computer : listAllComputer) {
			Optional<Company> optionalCompany = listCompany.stream()
					.filter(company -> company.getId() == computer.getCompany_id()).findFirst();
			String nameCompnay = optionalCompany.isPresent() ? optionalCompany.get().getName() : "";
			listAllComputerDto.add(ComputerMapper.mapDTO(computer, nameCompnay));
		}

		if (searchParam != null) {
			filterList = listAllComputerDto.stream()
					.filter(computerDto -> computerDto.getName().matches("(?i).*" + searchParam + ".*")
							| computerDto.getCompany().matches("(?i).*" + searchParam + ".*"))
					.collect(Collectors.toList());

		}

		for (ComputerDTO computerDtofilter : filterList) {
			Optional<Computer> optionalComputer = listAllComputer.stream()
					.filter(computer -> computer.getId() == computerDtofilter.getId()).findFirst();
			if (optionalComputer.isPresent()) {
				allListFilter.add(optionalComputer.get());
			}
		}

		return allListFilter;
	}


	public  List<ComputerDTO> getOrderListComputer(HttpServletRequest req, List<ComputerDTO> listComputer) {

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
}
