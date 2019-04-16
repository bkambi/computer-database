package com.excilys.cdb.controller.web;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.cdb.dao.CompanyDAO;
import com.excilys.cdb.dto.ComputerDTO;
import com.excilys.cdb.services.ComputerServices;

/**
 * Servlet implementation class ComputerEditServlet
 */

public class EditServlet extends HttpServlet {

	Logger logger = LoggerFactory.getLogger(EditServlet.class);

	private static final long serialVersionUID = 1L;

	@Autowired
	private ComputerServices computerServices;

	@Autowired
	private CompanyDAO dao;

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ComputerDTO computerDto = computerServices.getComputerDTO(request).isPresent()
				? computerServices.getComputerDTO(request).get()
				: null;

		if (computerDto == null) {
			response.sendRedirect("/dashboard");
		} else {
			request.setAttribute("computer", computerDto);
			request.setAttribute("listeCompany", dao.getList());
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/editComputer.jsp");
			rd.forward(request, response);
		}

	}
}
