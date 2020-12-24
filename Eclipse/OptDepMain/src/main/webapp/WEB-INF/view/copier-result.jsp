<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<title>Result of coping</title>
</head>

	<body>
	<h2>The coping result</h2>
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
			<br>
			<br>
			The next results are:
			<c:forEach var="resultFolder" items="${folder.quatityOfFiles}">
				<table>
					<tr>
					<td>${resultFolder}</td>
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