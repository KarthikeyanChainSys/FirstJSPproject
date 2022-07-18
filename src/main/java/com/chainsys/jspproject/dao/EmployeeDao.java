package com.chainsys.jspproject.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chainsys.jspproject.pojo.Emp;

public class EmployeeDao {
	/**
	 * getConnection()
	 * insertEmployee(Employee newemp)
	 * getEmployeeByID(int id)
	 * getAllEmployees()
	 * updateEMployee(Employee newemp)
	 * deleteEmployee(int id)
	 */
	private static Connection getConnection()
	{
		Connection con = null;
		String drivername = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "system";
		String pwd = "oracle";
		try {
				Class.forName(drivername);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
   		try {
   				con = DriverManager.getConnection(url,user,pwd);
   			} catch (SQLException e) {
   				e.printStackTrace();
   			}
   			return con;
	}
	
//	To insert new row to the table employees
	public static int insertEmployee(Emp newemp)
	{
		String insertquery = "insert into emp(EMP_ID,ENAME,CITY,SALARY) values (?,?,?,?)";
		Connection con = null;
		int rows = 0;
		PreparedStatement ps = null;
		try {
			con = getConnection();
			ps = con.prepareStatement(insertquery);
			ps.setInt(1,newemp.getEmp_id());
			ps.setString(2,newemp.getename());
			ps.setString(3,newemp.getCity());
			ps.setDouble(4,newemp.getSalary());
			rows = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rows;
	}
	
//	For updating all the columns of the table
	public static int updateEmployee(Emp newemp)
	{
		String updatequery = "update emp set ENAME=?,CITY=?,SALARY=? where emp_id=?";
		Connection con = null;
		int rows = 0;
		PreparedStatement ps = null;
		try {
			con = getConnection();
			ps = con.prepareStatement(updatequery);
			ps.setInt(4,newemp.getEmp_id());
			ps.setString(1,newemp.getename());
			ps.setString(2, newemp.getCity());
			ps.setFloat(3,newemp.getSalary());
			rows = ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rows;
	}
	

	
//	To delete a row from the table using id
	public static int deleteEmployee(int id)
	{
		String deletequery = "delete emp where emp_id=?";
		Connection con = getConnection();
		int rows = 0;
		PreparedStatement ps = null;
		try {
			con = getConnection();
			ps = con.prepareStatement(deletequery);
			ps.setInt(1,id);
			rows = ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rows;
	}
	
//	To retrieve all employee data from the table
	public static List<Emp> getAllEmployees()
	{
		List<Emp> listofEmployees = new ArrayList<Emp>();
		Emp emp = null;
		String selectquery = "select EMP_ID,ENAME,CITY,SALARY from emp";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			ps = con.prepareStatement(selectquery);
			rs = ps.executeQuery();
			while(rs.next())
			{
				emp = new Emp();
				emp.setEmp_id(rs.getInt(1));
				emp.setename(rs.getString(2));
				emp.setCity(rs.getString(3));
				emp.setSalary(rs.getFloat(4));
				listofEmployees.add(emp);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				ps.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			try {
				con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return listofEmployees;
	}
}
