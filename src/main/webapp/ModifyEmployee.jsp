<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Employee</title>
</head>
<body>
<form action="UpdateEmployee" method="post">
		<center>
		<div>
		    ID: <input type='text' name='id' placeholder="Employee Id">
		    </div>
		    <div>
			Name: <input type='text' name='ename' placeholder="Employee eName">
			</div>
			<div>
			City: <input type='text' name='city' placeholder="Employee city">
			</div>
			<div>
			Salary: <input type='text' name='salary' placeholder="00.000"><br>
			</div>
			<input type="submit" name='submit' value='update'>
		</center>
		</form>
</body>
</html>