package com.excilys.cdb.controllerWeb;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.cdb.DAO.CompanyDAO;



/**
 * Servlet implementation class ComputerServlet
 */
@WebServlet("/add-computer")
public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;  
	private CompanyDAO dao = new CompanyDAO();
			
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		request.setAttribute("listeCompany", dao.getList());
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/ addComputer.jsp");
		rd.forward(request, response);
	}
}
