package com.excilys.cdb.controllerWeb;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.cdb.DAO.CompanyDAO;
import com.excilys.cdb.DTO.ComputerDTO;
import com.excilys.cdb.services.ComputerServices;

/**
 * Servlet implementation class ComputerEditServlet
 */
@WebServlet("/edit-computer")
public class EditServlet extends HttpServlet {
	private CompanyDAO dao = new CompanyDAO();
	private static final long serialVersionUID = 1L;
      
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		ComputerDTO computerDto = ComputerServices.getComputerDTO(request).isPresent()? ComputerServices.getComputerDTO(request).get() : null; 
	
		if(computerDto == null) {
			response.sendRedirect("/dashboard");
		}else {
			request.setAttribute("computer", computerDto);
			request.setAttribute("listeCompany", dao.getList());
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/editComputer.jsp");
			rd.forward(request, response);
		}
	
	}
}
