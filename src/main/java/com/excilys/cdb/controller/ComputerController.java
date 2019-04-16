package com.excilys.cdb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.excilys.cdb.controller.web.EditServlet;
import com.excilys.cdb.dao.CompanyDAO;
import com.excilys.cdb.dto.ComputerDTO;
import com.excilys.cdb.exception.DeleteDataException;
import com.excilys.cdb.exception.InvalidDataComputerException;
import com.excilys.cdb.services.ComputerServices;

@Controller
public class ComputerController {

	Logger logger = LoggerFactory.getLogger(EditServlet.class);
	
	@Autowired
	private CompanyDAO companyDAO ;
	
	@Autowired
	private ComputerServices computerServices;
	
	@RequestMapping(value ="/add-computer",method = RequestMethod.GET)
	public String getAddComputerPage(Model model) {
		logger.info("inside getAddComputerPage method ");
		model.addAttribute("listeCompany", companyDAO.getList());
		return "addComputer" ;
	} 
	
	@RequestMapping(value ="/new-computer" ,method = RequestMethod.POST)
	public String addComputer(HttpServletRequest request, HttpServletResponse response) {
		String redirect = "";
		logger.info("inside addComputer method ");
		try {
			computerServices.handleRequestForAddComputer(request,response);
			logger.info(" success to add computer");
			redirect = "redirect:/dashboard";
		} catch (InvalidDataComputerException e) {
			logger.error("fail to add computer");
			e.printStackTrace();
			redirect = "redirect:/add-computer";
		}
		return redirect ;
	}
	
	@RequestMapping(value ="/edit-computer",method = RequestMethod.GET)
	public String getEditComputerPage(Model model,HttpServletRequest request, HttpServletResponse response) {
		logger.info("inside addComputer getEditComputerPage ");
		String redirect = "";
		ComputerDTO computerDto = computerServices.getComputerDTO(request).isPresent()
				? computerServices.getComputerDTO(request).get()
				: null;

		if (computerDto == null) {
			logger.info("computerDto is null auto-redirect to dashboard page");
			redirect = "redirect:/dashboard";
		} else {
			logger.info("computerDto is not null auto-redirect to editComputer page");
			model.addAttribute("computer", computerDto);
			model.addAttribute("listeCompany", companyDAO.getList());
			//RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/editComputer.jsp");
			//rd.forward(request, response);
			redirect = "editComputer";
		}
		return redirect ;
	} 
	
	@RequestMapping(value ="/editComputer" ,method = RequestMethod.POST)
	public String editComputer(HttpServletRequest request, HttpServletResponse response) {
		logger.info("inside addComputer editComputer ");
		String redirect = "";
		try {
			computerServices.handleRequestForUpdateComputer(request, response);
			logger.info(" success to edit computer");
			redirect = "redirect:/dashboard";
		} catch (InvalidDataComputerException e) {
			logger.error("fail to edit computer(s)");
			e.printStackTrace();
			redirect = "redirect:/edit-computer";
		}
		return redirect ;
	}
	
	@RequestMapping(value ="/delete-computer" ,method = RequestMethod.POST)
	public String deleteComputer(HttpServletRequest request, HttpServletResponse response) {
		logger.info("inside addComputer editComputer ");
		String redirect = "";
		try {
			computerServices.handleRequestForDeleteComputer(request,response);
			logger.info("success to delete computer(s)");
			redirect = "redirect:/dashboard";
		} catch (DeleteDataException e) {
			logger.error("fail to delete computer(s)");
			e.printStackTrace();
			redirect = "redirect:/edit-computer";
		} 
		return redirect ;
	}
	
}
