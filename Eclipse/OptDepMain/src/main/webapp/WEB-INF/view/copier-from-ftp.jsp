<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ page import="java.io.File"%>




<!DOCTYPE html>

<html>

<head>

<meta charset="utf-8" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />


<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/copier.css" />

<title>Copier from FTP</title>

</head>

<body>

	<div>
		<div id="headOfPage">
			<h2>FTP Kulakovskij</h2>
		</div>

		<div id="loginInfo">
			<security:authorize access="isAuthenticated()">
				<security:authentication property="principal.username"
					var="username" />
            	You are login as "<span style="font-size: 130%; color: red;text-transform: uppercase;"><b >  ${username}  </b> </span>"
            	<br>
				<a href="${pageContext.request.contextPath}/showMyLoginPage?logout">Logout</a>
			</security:authorize>
		</div>

	</div>
	<br>
	<br>	<br>
	<br>
	<hr>
	<div id="main">
		<security:authorize access="hasRole('DTGROUP')">
			<form:form action="${pageContext.request.contextPath}/dtgroup/copyResult"
				modelAttribute="folder" method="POST">

				<table id="tableCopier">
					<tr>
						<th>Driver</th>
						<th>Folder on FTP</th>
					</tr>


					<c:forEach var="folderName" items="${folder.folders}">
						<tr>
							<td><c:choose>

									<c:when
										test="${folderName=='RTU0010F33FDD2A' || folderName=='RTU0010F346CE64' 
							|| folderName=='RTU0010F346CE68' || folderName=='Paragon'}">
										<div class="secDriv">Second Driver</div>
									</c:when>


									<c:when
										test="${folderName=='RTU0010F346CD5E' || folderName=='RTU0010F346CE48'
							|| folderName=='RTU0010F364FE0A' || folderName=='Paragon2'}">
										<div class="karDriv">Karpeko</div>
									</c:when>


									<c:otherwise>3rd complect</c:otherwise>
								</c:choose></td>

							<td><form:checkbox path="checkedFolders"
									value="${folderName}" class="chkBxFld" id="checkBoxDr"/> <c:choose>

									<c:when
										test="${folderName=='RTU0010F33FDD2A' || folderName=='RTU0010F346CE64' 
							|| folderName=='RTU0010F346CE68' || folderName=='Paragon'}">
										<span class="secDriv">${folderName} </span>
									</c:when>


									<c:when
										test="${folderName=='RTU0010F346CD5E' || folderName=='RTU0010F346CE48'
							|| folderName=='RTU0010F364FE0A' || folderName=='Paragon2'}">
										<span class="karDriv">${folderName} </span>
									</c:when>


									<c:otherwise>${folderName}</c:otherwise>
								</c:choose></td>
						</tr>
					</c:forEach>


					<tr>
						<td><b>Fldr for Saving files on E disk</b></td>
						<td><form:input type="text" path="folderNameForSaving"
								size="30" accept-charset="utf-8" name="fld4Saving"
								value="${cookie['fld4Sav'].getValue()}" /></td>


					</tr>

				</table>
				<br>
				<input type="submit" value="Download LogFiles">
			</form:form>
		</security:authorize>
	</div>
	<br>
	<br>
	<a href="${pageContext.request.contextPath}/dtgroup/dt-main-page">Return to main
		page</a>



</body>



</html>