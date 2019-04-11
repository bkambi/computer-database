package com.excilys.cdb.controllerWeb;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.cdb.exception.InvalidDataComputerException;
import com.excilys.cdb.services.ComputerServices;

/**
 * Servlet implementation class ComputerEditServlet
 */
@WebServlet("/editComputer")
public class ComputerEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ComputerServices computerServices;
	
	public ComputerEditServlet(ComputerServices computerServices) {
		this.computerServices =computerServices;
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
