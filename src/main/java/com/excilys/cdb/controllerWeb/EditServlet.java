package com.excilys.cdb.controllerWeb;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.cdb.DAO.CompanyDAO;
import com.excilys.cdb.DTO.ComputerDTO;
import com.excilys.cdb.services.ComputerServices;

/**
 * Servlet implementation class ComputerEditServlet
 */
@WebServlet("/edit-computer")
public class EditServlet extends HttpServlet {
	  
	@Autowired
	private CompanyDAO dao ;
	private static final long serialVersionUID = 1L;
     
	private ComputerServices computerServices;
	
	@Autowired
	public EditServlet(ComputerServices computerServices) {
		this.computerServices = computerServices ;
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		ComputerDTO computerDto = computerServices.getComputerDTO(request).isPresent()? computerServices.getComputerDTO(request).get() : null; 
	
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
