package com.excilys.cdb.controllerWeb;

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
 * Servlet implementation class ComputerEditServlet
 */
@WebServlet("/editComputer")
public class ComputerEditServlet extends HttpServlet {
	
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			computerServices.handleRequestForUpdateComputer(request, response);
			response.sendRedirect("/dashboard");
		} catch (InvalidDataComputerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.sendRedirect("/edit-computer");
		}
	}

}
