package com.excilys.cdb.controller.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.cdb.config.SpringConfiguration;
import com.excilys.cdb.dto.ComputerDTO;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.services.ComputerServices;

/**
 * Servlet implementation class HomeServlet
 */

public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Logger logger = LoggerFactory.getLogger(HomeServlet.class);

	@Autowired
	private ComputerServices computerServices;

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
		HttpSession session = request.getSession();
		List<ComputerDTO> listeComputerDto = computerServices.getListComputerToShowService(request);
		List<String> listIndice = computerServices.getListIndice(request);
		String totalComputer = computerServices.getTotalComputer();

		// TODO filtrer la grande liste
		if (request.getParameter("orderBy") != null && request.getParameter("reversed") != null)
			listeComputerDto = computerServices.getOrderListComputer(request, listeComputerDto);
		else
			;

		request.setAttribute("listeComputer", listeComputerDto);
		request.setAttribute("listIndice", listIndice);
		request.setAttribute("totalCount", totalComputer);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/dashboard.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
