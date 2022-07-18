<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add new Employee</title>
</head>
<body>
	<form action="/FirstJSPproject/AddEmployee" method="post">
	<div>
			<label for="id">ID:</label> <input id="id" type="text" name="emp_id"
				placeholder="Enter your ID ">
		</div>
		<div>
			<label for="name">Name:</label> <input id="name" type="text" name="ename"
				placeholder="Enter your Name ">
		</div>
		<div>
			<label for="city">City:</label> <input id="city" type="text" name="city"
				placeholder="Enter your City ">
		</div>
		<div>
			<label for="salary">Salary:</label> <input id="salary" type="text" name="salary"
				placeholder="Enter your Salary ">
		</div>
		<input type=submit value="Add" name="submit">
	</form>
</body>
</html>