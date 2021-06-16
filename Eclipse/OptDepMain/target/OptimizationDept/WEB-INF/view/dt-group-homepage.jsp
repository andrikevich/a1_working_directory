<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="IUTF-8">
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/copier.css" />
<title>DT group home page</title>
</head>
<body>

<div>

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


<security:authorize access="hasRole('DTGROUP')">
	<a href= "${pageContext.request.contextPath}/dtgroup/copierFromFtp">Copier Utility from FTP</a>
	<br>
	<a href= "${pageContext.request.contextPath}/dtgroup/stuChooser">STU chooser</a>
	<br>
	<a href= "${pageContext.request.contextPath}/dtgroup/simCardSearcher">SIMCard chooser</a>	
</security:authorize>
</body>
</html>