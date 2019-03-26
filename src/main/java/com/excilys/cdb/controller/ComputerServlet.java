package com.excilys.cdb.controller;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.cdb.DAOImpl.ComputerDAOImpl;
import com.excilys.cdb.model.Computer;

/**
 * Servlet implementation class ComputerServlet
 */
@WebServlet("/add-computer")
public class ComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     ComputerDAOImpl dao = new ComputerDAOImpl();  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComputerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ComputerServices.handleRequestForAddComputer(request);
	}
}
