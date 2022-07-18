<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import = "java.util.List,com.chainsys.jspproject.pojo.Emp,java.util.ArrayList"
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>GetAllEmployees</title>
</head>
<body>
<table border="4" bgcolor="lightblue">
<thead> </thead>
<%
List<Emp> emplist = (ArrayList<Emp>)request.getAttribute("viewlist");
for(Emp emp : emplist){
%>
<tr>
<td> <%=emp.getEmp_id()%> </td>
<td> <%=emp.getename()%> </td>
<td> <%=emp.getCity()%> </td>
<td> <%=emp.getSalary()%> </td>
</tr>
<%}%>
</table>
</body>
</html>