<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<jsp:include page="header-sim.jsp"/>
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
			<th>Action</th>
		</tr>
		<c:forEach var="tempSimCard" items="${simCards}">

            <c:url var="updateLink" value="/dtgroup/showFormForUpdateSimCard">
                <c:param name="msisdn" value="${tempSimCard.msisdn}" />
            </c:url>

            <c:url var="detailInfo" value="/dtgroup/selectedSim">
                    <c:param name="msisdn" value="${tempSimCard.msisdn}" />
            </c:url>

		<c:set var = "simCardIccid"  value = "${tempSimCard.iccid}"/>

		<tr>
			<td>${tempSimCard.iccid}</td>
			<td>${tempSimCard.msisdn}</td>
			<td>${tempSimCard.device}</td>
			<td>${tempSimCard.description1}</td>
			<td>${tempSimCard.description2}</td>
			<td>${tempSimCard.additionalInfo}</td>
			<td><c:choose>

					<c:when
						test="${simCardIccid!='IsNotInMyDB'}">
								<a href="${updateLink}">Update</a>
                                 |
                                <a href="${detailInfo}">Details</a>

					</c:when>
					<c:otherwise>
           						 No action...
        			 </c:otherwise>
				</c:choose></td>

		</tr>

		</c:forEach>
		<br>
		<br>
		<br>
	</table>

	<br>
	<button onclick="goBack()" class="btn btn-secondary">Go Back</button>

	<script>
		function goBack() {
			window.history.back();
		}
	</script>

	<br>
	<a href="${pageContext.request.contextPath}/dtgroup/dt-main-page">Return to main page</a>



</body>
</html>