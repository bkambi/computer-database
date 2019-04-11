package com.excilys.cdb.controllerWeb;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.cdb.exception.InvalidDataComputerException;
import com.excilys.cdb.services.ComputerServices;

/**
 * Servlet implementation class ComputerAddServlet
 */
@WebServlet("/new-computer")
public class ComputerAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ComputerServices computerServices;
    /**
     * @see HttpServlet#HttpServlet()
     */
	@Autowired
    public ComputerAddServlet(ComputerServices computerServices) {
        super();
        this.computerServices = computerServices;
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
