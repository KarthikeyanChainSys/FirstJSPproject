package com.chainsys.jspproject.open;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

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
 * Servlet implementation class Employees
 */
@WebServlet("/Employees")
public class Employees extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Employees() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		List<Emp> emplist = EmployeeDao.getAllEmployees();
		Iterator<Emp> empItr = emplist.iterator();

		if (request.getParameter("input").equals("yes")) {
			response.setContentType("text/html");
			out.print("<html><head><title><Employees</title></head><body>");
			out.println("<body style=\"background-color:powderblue;\">");
			out.print("<table border=1px bgcolor=\"DeepSkyBlue\" width=50%>");
			out.print("<tr bgcolor=\"DeepSkyBlue\" align=center>");
			out.print("<th height=\"10\" width=\"90\">Emp_id:</th>");
			out.print("<th height=\"10\" width=\"90\">First_name:</th>");
			out.print("<th height=\"10\" width=\"90\">Last_name:</th>");
			out.print("<th height=\"10\" width=\"90\">Email:</th>");
			while (empItr.hasNext()) {
				out.print("<tr align=center>");
				Emp emp = empItr.next();
				out.print("<td bgcolor=\"SkyBlue\">" + emp.getEmp_id() + "</td>");
				out.print("<td bgcolor=\"SkyBlue\">" + emp.getename() + "</td>");
				out.print("<td bgcolor=\"SkyBlue\">" + emp.getCity() + "</td>");
				out.print("<td bgcolor=\"SkyBlue\">" + emp.getSalary() + "</td>");
				out.print("</tr>");
//			out.println("emp id:"+emp.getEmployee_id()+","+emp.getFirst_name()+","+
//					emp.getLast_name()+",");

			}
		} else
			out.println(" <br><h4>thank you for processing with us</h4>");
		out.print("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("submit").equals("Add")) {
			String source = "AddNewEmployee";
			String message = "<h1> Error while " + source + "</h1>";
			PrintWriter out = response.getWriter();
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
			out.println(result + "row inserted");
			try {
				out.close();
			} catch (Exception e) {
				message += " Error while inserting records </p>";
				String errorPage = ExceptionManager.handleExeption(e, source, message);
				out.print(errorPage);
				return;
			}
		} else if (request.getParameter("submit").equals("update")) {
			doPut(request, response);
		} else if (request.getParameter("submit").equals("Delete")) {
			doDelete(request, response);
		}
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String source = "UpdateEmployees";
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
			int result = EmployeeDao.updateEmployee(emp);
			out.println(result + "row Updated");
		} catch (Exception e) {
			message += " Error while updating records </p>";
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

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
