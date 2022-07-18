package com.chainsys.jspproject.open;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chainsys.jspproject.commonutil.ExceptionManager;
import com.chainsys.jspproject.commonutil.InvalidInputDataException;
import com.chainsys.jspproject.commonutil.Validator;
import com.chainsys.jspproject.dao.EmployeeDao;

/**
 * Servlet implementation class DeleteEmployee
 */
@WebServlet("/DeleteEmployee")
public class DeleteEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteEmployee() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String source = "DeleteEmployees";
		String message = "<h1> Error while " + source + "</h1>";
		PrintWriter out = response.getWriter();
		try {
		String emp_id = request.getParameter("id");
		try {
			Validator.checkStringForParseInt(emp_id);
		} catch (InvalidInputDataException e) {
			message += " Error in Employee id records input </p>";
			String errorPage = ExceptionManager.handleExeption(e, source, message);
			out.print(errorPage);
			return;
		}
		int id = Integer.parseInt(emp_id);
		int result = EmployeeDao.deleteEmployee(id);
		out.println(result + "row deleted");
		} catch (Exception e) {
			message += " Error while deleting records </p>";
			String errorPage = ExceptionManager.handleExeption(e, source, message);
			out.print(errorPage);
			return;
		}
		try {
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
