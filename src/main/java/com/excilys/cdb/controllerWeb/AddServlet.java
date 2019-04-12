package com.excilys.cdb.controllerWeb;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.cdb.DAO.CompanyDAO;



/**
 * Servlet implementation class ComputerServlet
 */
@WebServlet("/add-computer")
public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private CompanyDAO companyDAO ;

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
		request.setAttribute("listeCompany", companyDAO.getList());
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/addComputer.jsp");
		rd.forward(request, response);
	}
}
