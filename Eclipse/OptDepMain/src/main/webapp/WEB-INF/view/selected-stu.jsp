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
			<th>Action</th>
		</tr>


		<c:forEach var="stu" items="${stuList}">
		
		<!-- for update hyperlink -->
			<c:url var="updateLink" value="/showFormForUpdate">
				<c:param name="id" value="${stu.id}" />
			</c:url>
			
			<tr>
				<td>${stu.id}</td>
				<td>${stu.email}</td>
				<td>${stu.city}</td>
				<td>${stu.adress}</td>
				<td>${stu.responsiblePerson}</td>
				<td>${stu.phone}</td>
				<td><a href="${updateLink}">Update</a></td>
			</tr>
		</c:forEach>
	</table>


</body>
</html>