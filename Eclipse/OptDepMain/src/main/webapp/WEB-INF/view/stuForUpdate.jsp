<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta charset="UTF-8">
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/stu.css" />
<title>Selected STU</title>
</head>
<body>
	<form:form action="updatedStu" method="GET" modelAttribute="stu">
		<form:hidden path="id" />

		<h1> STU number:  ${stu.id}</h1>
		<table border="1" >
			<tr bgcolor="D6D2D1">
				<td class="header-column">City</td>
				<td>${stu.city}</td>
			</tr>
			<tr bgcolor="D6D2D1">
				<td class="header-column">Adress</td>
				<td>${stu.adress}</td>
			</tr>
			<tr>
				<td class="header-column">Email</td>
				<td><form:input path="email" cssClass="inputCell" /></td>
			</tr>
			<tr>
				<td class="header-column">Responsible Person</td>
				<td><form:input path="responsiblePerson" cssClass="inputCell"/></td>
			</tr>

		</table>

		<br><br>
		<input type="submit" value="Update STU" class="applyButton">
	</form:form>


		<br>
		<br>
		<a href="${pageContext.request.contextPath}/dtgroup/dt-main-page">Return to main page</a>

</body>
</html>