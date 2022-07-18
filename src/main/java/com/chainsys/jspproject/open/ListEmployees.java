package com.chainsys.jspproject.open;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chainsys.jspproject.dao.EmployeeDao;
import com.chainsys.jspproject.pojo.Emp;

/**
 * Servlet implementation class Employees
 */
@WebServlet("/ListEmployees")
public class ListEmployees extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListEmployees() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Emp> emplist = EmployeeDao.getAllEmployees();
			response.setContentType("text/html");
			request.setAttribute("viewlist", emplist);
			RequestDispatcher rd = request.getRequestDispatcher("/ViewEmployee.jsp");
			rd.forward(request, response);
    }
}
