package com.excilys.cdb.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import com.excilys.cdb.dto.ComputerDTO;
import com.excilys.cdb.services.ComputerServices;

@Controller
public class DashboardController {
	Logger logger = LoggerFactory.getLogger(DashboardController.class);

	@Autowired
	private ComputerServices computerServices;

	@RequestMapping(value = { "/" ,"/dashboard"}, method = RequestMethod.GET)
	public String getDashboardPage(@RequestParam(value = "orderBy", required = false) String orderBy,
			@RequestParam(value = "reversed", required = false) String reversed,Model model,HttpServletRequest request, HttpServletResponse response) {

		logger.info(" inside DashboardController : getDashboardPage() method");
		List<ComputerDTO> listeComputerDto = computerServices.getListComputerToShowService(request);
		List<String> listIndice = computerServices.getListIndice(request);
		String totalComputer = computerServices.getTotalComputer();

		if (request.getParameter("orderBy") != null && request.getParameter("reversed") != null)
			listeComputerDto = computerServices.getOrderListComputer(request, listeComputerDto);
		
		model.addAttribute("listeComputer", listeComputerDto);
		model.addAttribute("listIndice", listIndice);
		model.addAttribute("totalCount", totalComputer);
		
		return "dashboard";
	}
}
