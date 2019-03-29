package com.excilys.cdb.controllerWeb;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.excilys.cdb.DTO.ComputerDTO;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.services.ComputerServices;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/dashboard")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<ComputerDTO> listeComputerDto = ComputerServices.getListComputerToShowService(request);
		List<String> listIndice = ComputerServices.getListIndice();
		String totalComputer = ComputerServices.getTotalComputer();
		
		if(request.getParameter("search")!= null )
			listeComputerDto = ComputerServices.getFilterListComputer(request, listeComputerDto);
		else if(request.getParameter("orderBy")!=null && request.getParameter("reversed")!=null)
			listeComputerDto = ComputerServices.getOrderListComputer(request, listeComputerDto);
		else ;
			
		request.setAttribute("listeComputer", listeComputerDto);
		request.setAttribute("listIndice",listIndice);
		request.setAttribute("totalCount", totalComputer);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/dashboard.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
