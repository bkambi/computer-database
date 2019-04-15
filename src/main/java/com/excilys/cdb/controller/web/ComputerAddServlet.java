package com.excilys.cdb.controller.web;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.cdb.exception.InvalidDataComputerException;
import com.excilys.cdb.services.ComputerServices;

/**
 * Servlet implementation class ComputerAddServlet
 */
@WebServlet("/new-computer")
public class ComputerAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ComputerServices computerServices;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());

	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			computerServices.handleRequestForAddComputer(request,response);
			response.sendRedirect("/dashboard");
		} catch (InvalidDataComputerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.sendRedirect("/add-computer");
		}
	}

}
