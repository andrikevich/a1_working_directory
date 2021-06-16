<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>

<html>
	<head>
	<meta charset="UTF-8">
	<title>Input STU parameters </title>
	</head>
	<body>
	<h1>Please input any of STU parameter</h1>
	<form:form action="selectedStu" method="GET">
		<table>
		<tr>
			<td>Input STU ID</td>
			<td><input type="number" name="id" title="i.e. 2400" min="2400" max="2470" width="50"></td>
		</tr>
		<tr><td align="center" colspan="2">or</td></tr>
		<tr>
			<td>Input city</td>
			<td><input type="text" name="city" title="i.e. Минск" width="50"></td>
		</tr>
		</table>
		
		
		<input type="submit" value="Search" >	
	</form:form>

		<br>
		<br>
		<a href="${pageContext.request.contextPath}/dtgroup/dt-main-page">Return to main page</a>
	</body>
</html>