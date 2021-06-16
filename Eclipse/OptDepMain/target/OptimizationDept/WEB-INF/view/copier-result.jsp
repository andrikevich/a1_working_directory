<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/copier.css" />
	<title>Result of coping</title>
</head>

	<body>
		<div>
		<div id="headOfPage">
				<h2>The coping result</h2>
		</div>

		<div id="loginInfo">
			<security:authorize access="isAuthenticated()">
				<security:authentication property="principal.username"	var="username" />
            	You are login as "<span style="font-size: 130%; color: red;text-transform: uppercase;"><b >${username}</b> </span>"
            	<br>
				<a href="${pageContext.request.contextPath}/showMyLoginPage?logout">Logout</a>
			</security:authorize>
		</div>

	</div>
	
	<br>
	<br>	<br>
	<br>
	<hr>

	<br>
	<div>
		    It was downloaded from folder: 
		    <c:forEach var="fldrFrom" items="${folder.checkedFolders}">
		    <ul>
		    	<li><i>${fldrFrom}</i></li>
		    </ul>
		    </c:forEach>
		    
			<br>
			to target folder: <i>${folder.folderNameForSaving}</i>
			<br><br>
			The next results are:
			<br><br>
			<c:forEach var="resultFolder" items="${folder.quatityOfFiles}">
				<table id="result-folder">
					<tr id="result-folder">
					<td id="result-folder">${resultFolder}</td>
					</tr>
				</table>
			</c:forEach>
			<hr>
			<h3>There were downloaded ${countOfDownloadedFiles} files!</h3>
			
	</div>
			
	<br>
	<br>
	<a href="${pageContext.request.contextPath}/dtgroup/dt-main-page">Return to main
		page</a>

	
	</body>
</html>