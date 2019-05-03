package com.excilys.cdb.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.cdb.dao.dao.ComputerDAO;
import com.excilys.cdb.dao.repositories.CompanyRepository;
import com.excilys.cdb.dao.repositories.ComputerRepository;
import com.excilys.cdb.dto.ComputerDTO;
import com.excilys.cdb.exception.DeleteDataException;
import com.excilys.cdb.exception.InvalidDataComputerException;
import com.excilys.cdb.exception.NoDataFoundException;
import com.excilys.cdb.mapper.ComputerMapper;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.util.enume.OrderBy;

@Service
public class ComputerServices {

	private Logger logger = Logger.getLogger(ComputerServices.class.getName());

	@Autowired
	CompanyRepository companyRepository ;
	
	@Autowired
	ComputerRepository computerRepository;
	
	@Autowired
	private ComputerDAO daoComputer;

	private Page pageDashboard;

	/**
	 * Return a Computer DTO if is not null : using Optional and the method of the
	 * DAO
	 * 
	 * @return ComputerDTO , DTO useful for the view
	 */
	public List<ComputerDTO> getListComputerToShowService(HttpServletRequest req) {

		//System.out.println(companyRepository.findAll());
		pageDashboardInit(req);

		List<Computer> listComputer = pageDashboard.getListeComputerToShow();
		List<ComputerDTO> listComputerDTO = new ArrayList<ComputerDTO>();
		List<Company> listCompany = pageDashboard.getListeCompany();

		for (Computer computer : listComputer) {
			Optional<Company> optionalCompany = listCompany.stream()
					.filter(company -> company.getId() == computer.getCompanyId()).findFirst();
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

	public void handleRequestForAddComputer(ComputerDTO computerDto) throws InvalidDataComputerException {

		Computer computer;
		Optional<Computer> optionalComputer = ComputerMapper.mapViewDtoToComputer(computerDto);
		computer = optionalComputer.isPresent() ? optionalComputer.get() : new Computer();
		if (isValidatedByTheBack(computer)) {
			computerRepository.save(computer);
		} else {
			throw new InvalidDataComputerException();
		}
	}

	/**
	 * Update a Computer in the data base using the method of the DAO
	 * 
	 * @throws InvalidDataComputerException
	 */

	public void handleRequestForUpdateComputer(ComputerDTO computerDto) throws InvalidDataComputerException {
		Computer computer;
		Optional<Computer> optionalComputer = ComputerMapper.mapViewDtoToComputer(computerDto);
		computer = optionalComputer.isPresent() ? optionalComputer.get() : new Computer();
		if (isValidatedByTheBack(computer)) {
			computerRepository.save(computer);
		} else {
			throw new InvalidDataComputerException();
		}
	}

	/**
	 * Delete a Computer in the data base using the method of the DAO
	 * 
	 * @throws InvalidDataComputerException
	 */

	public void handleRequestForDeleteComputer(HttpServletRequest request, HttpServletResponse response)
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
			Optional<Company> optionalCompany =companyRepository.findById(computer.getCompanyId());
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
	public List<String> getListIndice(HttpServletRequest req) {
		HttpSession session = req.getSession();
		List<String> listIndiceRetour = new ArrayList<String>();
		pageDashboard = (Page) session.getAttribute("pageDashboard");
		int max = pageDashboard.getListeComputer().size() / pageDashboard.getNumberOfComputer();
		int indice = pageDashboard.getIndice();

		if (indice - 1 >= 0 & indice + 1 <= max) {
			listIndiceRetour = Arrays.asList(String.valueOf(indice - 1), String.valueOf(indice), String.valueOf(indice + 1));
		} else if (indice - 1 < 0 & indice + 1 <= max) {
			listIndiceRetour = Arrays.asList(String.valueOf(indice), String.valueOf(indice + 1));
		} else if (pageDashboard.getIndice() - 1 >= 0 & pageDashboard.getIndice() + 1 > max) {
			listIndiceRetour = Arrays.asList(String.valueOf(indice - 1), String.valueOf(indice));
		} else {
			listIndiceRetour = Arrays.asList("0");
		}
		return listIndiceRetour;
	}

	/**
	 * Instance the Dashboard page for the dashboard.jsp
	 * 
	 * @param req
	 */
	public void pageDashboardInit(HttpServletRequest req) {
		HttpSession session = req.getSession();

		String indiceString = (String) req.getParameter("indice");
		String numberOfComputerString = (String) req.getParameter("numberOfComputer");
		String arrayIndice = (String) req.getParameter("arrayIndice");
		String searchParam = req.getParameter("search");

		int indice = 0;
		int numberOfComputer = 10;

		if (session.getAttribute("pageDashboard") == null) {
			pageDashboard = new Page();
			pageDashboard.setListeComputer(computerRepository.findAll());
			pageDashboard.setListeCompany(companyRepository.findAll());
			pageDashboard.setIndice(indice);
			pageDashboard.updateListComputerWithNewNumberOfComputer(numberOfComputer);

			session.setAttribute("pageDashboard", pageDashboard);
		} else {
			pageDashboard = (Page) session.getAttribute("pageDashboard");
			pageDashboard.setListeCompany(companyRepository.findAll());
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
			}
			if (req.getParameter("orderBy") != null & req.getParameter("reversed") != null) {

				List<Computer> listComputer = this.getOrderListComputer(req.getParameter("orderBy"),
						req.getParameter("reversed"), pageDashboard.getListeComputer());
				pageDashboard.setListeComputer(listComputer);
			}
			if (searchParam == null & req.getParameter("orderBy") == null & req.getParameter("reversed") == null) {
				pageDashboard.setListeComputer(computerRepository.findAll());
			}
			
			if (numberOfComputerString != null) {
				pageDashboard.setNumberOfComputer(numberOfComputer);
			} if (indiceString != null) {
				pageDashboard.setIndice(indice);
			} if (arrayIndice != null) {
				pageDashboard.setIndice(indice);
			}
			pageDashboard.updateListComputerWithNewNumberOfComputer(numberOfComputer);
			session.setAttribute("pageDashboard", pageDashboard);
		}
	}

	public Optional<ComputerDTO> getComputerDTO(Long computerID) {

		ComputerDTO computerDto = null;
		try {
			Computer computer = computerRepository.findById(computerID).orElseThrow(() -> new NoDataFoundException());
			Optional<Company> optionalCompany = pageDashboard.getListeCompany().stream()
					.filter(company -> company.getId() == computer.getCompanyId()).findFirst();
			String nameCompnay = optionalCompany.isPresent() ? optionalCompany.get().getName() : "";
			computerDto = ComputerMapper.mapDTO(computer, nameCompnay);
		} catch (NoDataFoundException e) {
			logger.error("No Data Found Exception : fail to get computer with id = " + computerID);
		}

		return Optional.ofNullable(computerDto);

	}

	public String getTotalComputer() {
		return String.valueOf(pageDashboard.getListeComputer().size());

	}

	/**
	 * Filter the list by name la liste visible
	 * 
	 * @param req
	 * @param listComputer
	 * @return
	 */
	public List<Computer> getFilterAllListComputer(String searchParam) {


		List<Computer> listAllComputer = computerRepository.findAll();
		List<Computer> listAllFilter = new ArrayList<Computer>();

		List<Company> listCompany = pageDashboard.getListeCompany();

		List<ComputerDTO> listFilter = new ArrayList<ComputerDTO>();
		List<ComputerDTO> listAllComputerDto = new ArrayList<ComputerDTO>();

		listAllComputerDto = ComputerMapper.mapListDTO(listAllComputer, listCompany).get();

		if (searchParam != null) {
			listFilter = listAllComputerDto.stream()
					.filter(computerDto -> computerDto.getName().matches("(?i).*" + searchParam + ".*")
							| computerDto.getCompany().matches("(?i).*" + searchParam + ".*"))
					.collect(Collectors.toList());

		}
		listAllFilter = ComputerMapper.mapList(listFilter, listAllComputer).get();

		return listAllFilter;
	}

	public List<Computer> getOrderListComputer(String orderByParam, String reverseParam, List<Computer> listComputer) {

		int value = Integer.valueOf(orderByParam);
		List<Computer> filtListFinal = new ArrayList<Computer>();
		List<ComputerDTO> filtList = new ArrayList<ComputerDTO>();
		List<Company> listCompany = pageDashboard.getListeCompany();
		List<ComputerDTO> listAllComputerDto = new ArrayList<ComputerDTO>();

		listAllComputerDto = ComputerMapper.mapListDTO(listComputer, listCompany).get();

		switch (OrderBy.values()[value - 1]) {
		case COMPUTER_NAME:
			filtList = "0".equals(reverseParam)
					? listAllComputerDto.stream().sorted(Comparator.comparing(ComputerDTO::getName))
							.collect(Collectors.toList())
					: listAllComputerDto.stream().sorted(Comparator.comparing(ComputerDTO::getName).reversed())
							.collect(Collectors.toList());
			break;
		case COMPUTER_INTRODUCED:
			filtList = "0".equals(reverseParam)
					? listAllComputerDto.stream().sorted(Comparator.comparing(ComputerDTO::getIntroduced))
							.collect(Collectors.toList())
					: listAllComputerDto.stream().sorted(Comparator.comparing(ComputerDTO::getIntroduced).reversed())
							.collect(Collectors.toList());
			break;
		case COMPUTER_DISCONTINUED:
			filtList = "0".equals(reverseParam)
					? listAllComputerDto.stream().sorted(Comparator.comparing(ComputerDTO::getDiscontinued))
							.collect(Collectors.toList())
					: listAllComputerDto.stream().sorted(Comparator.comparing(ComputerDTO::getDiscontinued).reversed())
							.collect(Collectors.toList());
			break;
		case COMPUTER_COMPANY:
			filtList = "0".equals(reverseParam)
					? listAllComputerDto.stream().sorted(Comparator.comparing(ComputerDTO::getCompany))
							.collect(Collectors.toList())
					: listAllComputerDto.stream().sorted(Comparator.comparing(ComputerDTO::getCompany).reversed())
							.collect(Collectors.toList());
			break;
		}

		filtListFinal = ComputerMapper.mapList(filtList, listComputer).get();

		return filtListFinal;
	}
}
