<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	<%@ page import="java.util.List,java.util.ArrayList"%>
<%@ page import="com.chainsys.jspproject.pojo.Emp"%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert Employee</title>
</head>
<body>
<h1>Employee added successfully</h1>
<%Integer result = (Integer)request.getAttribute("result"); %>
<%= result %>
<a href="ListEmployees">View</a> 
</body>
</html>