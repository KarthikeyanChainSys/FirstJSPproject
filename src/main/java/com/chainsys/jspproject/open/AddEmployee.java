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
 * Servlet implementation class AddNewEmployee
 */
@WebServlet("/AddEmployee")
public class AddEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddEmployee() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String source = "AddNewEmployee";
		String message = "<h1> Error while " + source + "</h1>";
		Emp emp = new Emp();
		String emp_id = request.getParameter("emp_id");
		System.out.println("id " + emp_id);
		try {
			Validator.checkStringForParseInt(emp_id);
		} catch (InvalidInputDataException e) {
			System.out.println(e.getMessage());
			message += " Error in Employee id input </p>";
			String errorPage = ExceptionManager.handleExeption(e, source, message);
			out.print(errorPage);
			return; // Terminates the code execution beyond this point.
		}
		int id = Integer.parseInt(emp_id);
		try {
			Validator.CheckNumberForGreaterThanZero(id);
		} catch (InvalidInputDataException e) {
			message += " Error in Employee id input </p>";
			String errorPage = ExceptionManager.handleExeption(e, source, message);
			out.print(errorPage);
			return;
		}
		emp.setEmp_id(id);

		String ename = request.getParameter("ename");
		try {
			Validator.checkStringOnly(ename);
		} catch (InvalidInputDataException e) {
			message += " Error in Employee first name input </p>";
			String errorPage = ExceptionManager.handleExeption(e, source, message);
			out.print(errorPage);
			return;
		}
		emp.setename(ename);
		String city = request.getParameter("city");
		try {
			Validator.checkStringOnly(city);
		} catch (InvalidInputDataException e) {
			message += " Error in Employee last name input </p>";
			String errorPage = ExceptionManager.handleExeption(e, source, message);
			out.print(errorPage);
			return;
		}
		emp.setCity(city);

		String salary = null;
		try {
			salary = request.getParameter("salary");
			Validator.checkStringForParseInt(salary);
		} catch (InvalidInputDataException e) {
			message += " Error in Employee salary input </p>";
			String errorPage = ExceptionManager.handleExeption(e, source, message);
			out.print(errorPage);
			return;
		}
		float sal = Float.parseFloat(salary);
		emp.setSalary(sal);
		int result = EmployeeDao.insertEmployee(emp);
//			out.println(result + "row inserted");
		request.setAttribute("result", result);
		RequestDispatcher rd = request.getRequestDispatcher("/InsertEmployee.jsp");
		rd.forward(request, response);
		try {
			out.close();
		} catch (Exception e) {
			message += " Error while inserting records </p>";
			String errorPage = ExceptionManager.handleExeption(e, source, message);
			out.print(errorPage);
			return;
		}

	}
}
