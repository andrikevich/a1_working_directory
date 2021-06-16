<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/simCard.css" />
	
	
<title>Input SimCard parameters</title>
</head>
<body>
	<h1>Please input any of SimCard parameter</h1>
	<form:form name="simCardSelection" action="${pageContext.request.contextPath}/dtgroup/selectedSims" method="GET">
		<c:if test="${error != null}">

			<div class="alert-message">
				Invalid sim-card parameter. Please insert correct sim card parameter</div>
				<br><br>

		</c:if>

		<table>
			<tr>
				<td>Input customer search param</td>
				<td><input type="text" name="searchParam" title="i.e. 1155365"></td>
			</tr>

		</table>


		<input type="submit" value="Search">
	</form:form>

</body>
</html>