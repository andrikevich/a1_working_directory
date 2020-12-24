<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/stu.css" />
<title>SimCard result</title>
</head>
<body>
	<h2>Result</h2>
	<br>
	<table>
		<tr>
			<th>iccid</th>
			<th>msisdn</th>
			<th>device</th>
			<th>description_1</th>
			<th>description_2</th>
			<th>additional_info</th>
			<th>Info from WIX</th>
			<th>Action</th>
		</tr>

		<c:url var="updateLink" value="/dtgroup/showFormForUpdateSimCard">
			<c:param name="msisdn" value="${simCard.msisdn}" />
		</c:url>
		<c:set var = "simCardIccid"  value = "${simCard.iccid}"/>

		<tr>
			<td>${simCard.iccid}</td>
			<td>${simCard.msisdn}</td>
			<td>${simCard.device}</td>
			<td>${simCard.description1}</td>
			<td>${simCard.description2}</td>
			<td>${simCard.additionalInfo}</td>
			<td>${info}</td>
			<td><c:choose>

					<c:when
						test="${simCardIccid!='Isn\\'tInMyDB'}">
								<a href="${updateLink}">Update</a>
					</c:when>
					<c:otherwise>
           						 No action...
        			 </c:otherwise>
				</c:choose></td>
		</tr>
		<br>
		<br>
		<br>
	</table>
	<table>
		<tr>
			<th>Webota Info</th>
		</tr>
		<tr>
			<td style="text-align: left">${weBotaInfo}</td>
		</tr>

	</table>
	<br>
	<br>
	<a href="${pageContext.request.contextPath}/dtgroup/dt-main-page">Return
		to main page</a>



</body>
</html>