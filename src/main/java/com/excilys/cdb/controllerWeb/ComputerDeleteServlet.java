package com.excilys.cdb.controllerWeb;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.cdb.exception.DeleteDataException;
import com.excilys.cdb.services.ComputerServices;

/**
 * Servlet implementation class ComputerDeleteServlet
 */
@WebServlet("/delete-computer")
public class ComputerDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			ComputerServices.handleRequestForDeleteComputer(request,response);
			response.sendRedirect("/training-java/dashboard");
		} catch (DeleteDataException e) {
			e.printStackTrace();
		}
	}
}
