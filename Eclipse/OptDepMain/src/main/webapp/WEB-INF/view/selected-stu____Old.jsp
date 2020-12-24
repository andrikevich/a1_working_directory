<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/stu.css" />
<title>STU result</title>
</head>
<body>
	<h2>Result</h2>
	<br>
	<table>
		<tr>
			<th>STU Id</th>
			<th>Email</th>
			<th>City</th>
			<th>Adress</th>
			<th>Responsible Person</th>
			<th>Mobile Phone</th>
		</tr>
		
		<tr>
			<td>${stu.id}</td>
			<td>${stu.email}</td>
			<td>${stu.city}</td>
			<td>${stu.adress}</td>
			<td>${stu.responsiblePerson}</td>
			<td>${stu.phone}</td>
		</tr>
	</table>
	
	
</body>
</html>