package com.excilys.cdb.controller;

import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.cdb.dao.CompanyDAO;
import com.excilys.cdb.dto.ComputerDTO;
import com.excilys.cdb.exception.DeleteDataException;
import com.excilys.cdb.exception.InvalidDataComputerException;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.services.ComputerServices;

@Controller
public class ComputerController {

	Logger logger = LoggerFactory.getLogger(ComputerController.class);

	@Autowired
	private CompanyDAO companyDAO;

	@Autowired
	private ComputerServices computerServices;

	@RequestMapping(value = "/add-computer", method = RequestMethod.GET)
	public ModelAndView getAddComputerPage() {
		logger.info("inside getAddComputerPage method ");
		ComputerDTO computerDto = new ComputerDTO();
		Map<Long, String> companies = companyDAO.getList().stream()
				.collect(Collectors.toMap(Company::getId, Company::getName));
		ModelAndView mav = new ModelAndView("addComputer");
		mav.addObject("addComputerForm", computerDto);
		mav.addObject("listeCompany", companies);
		return mav;
	}

	@RequestMapping(value = "/new-computer", method = RequestMethod.POST)
	public String addComputer(@ModelAttribute("addComputerForm") ComputerDTO computerDto) {
		String redirect = "";
		logger.info("inside addComputer method ");
		try {
			computerServices.handleRequestForAddComputer(computerDto);
			logger.info(" success to add computer");
			redirect = "redirect:/dashboard";
		} catch (InvalidDataComputerException e) {
			logger.error("fail to add computer");
			e.printStackTrace();
			redirect = "redirect:/add-computer";
		}
		return redirect;
	}

	@RequestMapping(value = "/edit-computer-{id}", method = RequestMethod.GET)
	public ModelAndView getEditComputerPage(Model model,@PathVariable(value = "id") Long computerId) {
		logger.info("inside addComputer getEditComputerPage ");
		
		ComputerDTO computerDto = computerServices.getComputerDTO(computerId).isPresent()
				? computerServices.getComputerDTO(computerId).get()
				: null;
		Map<Long, String> companies = companyDAO.getList().stream()
				.collect(Collectors.toMap(Company::getId, Company::getName));
		ModelAndView mav = new ModelAndView();
		if (computerDto == null) {
			logger.info("computerDto is null auto-redirect to dashboard page");
			mav.setViewName("redirect:/dashboard");
		} else {
			logger.info("computerDto is not null auto-redirect to editComputer page");
			mav.addObject("computerDtoId", computerId);
			mav.addObject("editComputerForm", computerDto);
			mav.addObject("listeCompany", companies);
			mav.setViewName("editComputer");
		}
		
		return mav;
	}

	@RequestMapping(value = "/editComputer", method = RequestMethod.POST)
	public String editComputer(@ModelAttribute("editComputerForm") ComputerDTO computerDto) {
		logger.info("inside addComputer editComputer ");
		String redirect = "";
		try {
			computerServices.handleRequestForUpdateComputer(computerDto);
			logger.info(" success to edit computer");
			redirect = "redirect:/dashboard";
		} catch (Exception e) {
			logger.error("fail to edit computer(s)");
			e.printStackTrace();
			redirect = "redirect:/edit-computer";
		}
		return redirect;
	}

	@RequestMapping(value = "/delete-computer", method = RequestMethod.POST)
	public String deleteComputer(HttpServletRequest request, HttpServletResponse response) {
		logger.info("inside addComputer editComputer ");
		String redirect = "";
		try {
			computerServices.handleRequestForDeleteComputer(request, response);
			logger.info("success to delete computer(s)");
			redirect = "redirect:/dashboard";
		} catch (DeleteDataException e) {
			logger.error("fail to delete computer(s)");
			e.printStackTrace();
			redirect = "redirect:/edit-computer";
		}
		return redirect;
	}

}
