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
	<form:form action="updatedSimCard" method="POST"
		modelAttribute="simCard">
		<form:hidden path="id" />

		<h1>MSISDN: ${simCard.msisdn}</h1>
		<table border="1">
			<tr bgcolor="D6D2D1">
				<td class="header-column">iccid</td>
				<td><form:input path="iccid" cssClass="inputCellHeader" readonly="true"
						 /></td>
			</tr>
			<tr>
			<tr bgcolor="D6D2D1">
				<td class="header-column">msisdn</td>
				<td><form:input path="msisdn" cssClass="inputCellHeader"
						readonly="true" /></td>
			</tr>
			<tr>
				<td class="header-column">Device</td>
				<td><form:input path="device" cssClass="inputCell" /></td>
			</tr>
			<tr>
				<td class="header-column">Description1</td>
				<td><form:input path="description1" cssClass="inputCell" /></td>
			</tr>
			<tr>
				<td class="header-column">Description2</td>
				<td><form:input path="description2" cssClass="inputCell" /></td>
			</tr>
			<tr>
				<td class="header-column">Additional Info</td>
				<td><form:input path="additionalInfo" cssClass="inputCell" /></td>
			</tr>

		</table>

		<br>
		<br>
		<input type="submit" value="Update SimCard Info" class="applyButton">
	</form:form>


	<br>
	<br>
	<a href="${pageContext.request.contextPath}/dtgroup/dt-main-page">Return to main
		page</a>

</body>
</html>