<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="IUTF-8">
<title>DT group home page</title>
</head>
<body>
<security:authorize access="hasRole('DTGROUP')">
	<a href= "${pageContext.request.contextPath}/dtgroup/copierFromFtp">Copier Utility from FTP</a>
	<br>
	<a href= "${pageContext.request.contextPath}/dtgroup/stuChooser">STU chooser</a>
	<br>
	<a href= "${pageContext.request.contextPath}/dtgroup/simCardSearcher">SIMCard chooser</a>	
</security:authorize>
</body>
</html>