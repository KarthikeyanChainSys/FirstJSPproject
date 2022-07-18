package com.chainsys.jspproject.open;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chainsys.jspproject.commonutil.ExceptionManager;
import com.chainsys.jspproject.commonutil.InvalidInputDataException;
import com.chainsys.jspproject.commonutil.Validator;
import com.chainsys.jspproject.dao.EmployeeDao;
import com.chainsys.jspproject.pojo.Emp;

/**
 * Servlet implementation class ModifyEmployee
 */
@WebServlet("/UpdateEmployee")
public class UpdateEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateEmployee() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String source = "UpdateEmployee";
		String message = "<h1> Error while " + source + "</h1>";
		PrintWriter out = response.getWriter();
		Emp emp = new Emp();
		try {

			String emp_id = request.getParameter("id");
			try {
				Validator.checkStringForParseInt(emp_id);
			} catch (InvalidInputDataException e) {
				message += " Error in Employee id records input </p>";
				String errorPage = ExceptionManager.handleExeption(e, source, message);
				out.print(errorPage);
			}
			int id = Integer.parseInt(emp_id);
			try {
				Validator.CheckNumberForGreaterThanZero(id);
			} catch (InvalidInputDataException e) {
				message += " Error in Employee id records input </p>";
				String errorPage = ExceptionManager.handleExeption(e, source, message);
				out.print(errorPage);
			}
			emp.setEmp_id(id);
			String emp_Firstname = request.getParameter("ename");
			
			try {
				Validator.checkStringOnly(emp_Firstname);
			} catch (InvalidInputDataException e) {
				System.out.println("ERROR: " + e.getMessage());
				message += " Error in Employee first name records input </p>";
				String errorPage = ExceptionManager.handleExeption(e, source, message);
				out.print(errorPage);
				return;
			}
			emp.setename(emp_Firstname);
			String city = request.getParameter("city");
			try {
				Validator.checkStringOnly(city);
			} catch (InvalidInputDataException e) {
				message += " Error in Employee city records input </p>";
				String errorPage = ExceptionManager.handleExeption(e, source, message);
				out.print(errorPage);
				return;
			}
			emp.setCity(city);
			String emp_salary = request.getParameter("salary");
			try {
				Validator.checkStringForParseInt(emp_salary);
			} catch (InvalidInputDataException e) {
				message += " Error in Employee salary records input </p>";
				String errorPage = ExceptionManager.handleExeption(e, source, message);
				out.print(errorPage);
				return;

			}
			float salary = Float.parseFloat(emp_salary);
			emp.setSalary(salary);
			
		} catch (Exception e) {
			message += " Error while updating records </p>";
			String errorPage = ExceptionManager.handleExeption(e, source, message);
			out.print(errorPage);
			return;
		}
		
		int result = EmployeeDao.updateEmployee(emp);
		out.println(result + "row Updated");
		request.setAttribute("result",result);
		RequestDispatcher rd = request.getRequestDispatcher("/Update.jsp");
		rd.forward(request, response);
	}

}
