<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>

<html>
	<head>
	<meta charset="UTF-8">
	<title>Input SimCard parameters </title>
	</head>
	<body>
	<h1>Please input any of SimCard parameter</h1>
	<form:form action="selectedSim" method="GET">
		<table>
		<tr>
			<td>Input customer number MSISDN</td>
			<td><input type="text" name="msisdn" title="i.e. 375291155365" ></td>
		</tr>
		<tr><td align="center" colspan="2">or</td></tr>
		<tr>
			<td>Input sim number ID from card</td>
			<td><input type="text" name="iccid" title="i.e. ****" "></td>
		</tr>
		</table>
		
		
		<input type="submit" value="Search" >	
	</form:form>

	</body>
</html>