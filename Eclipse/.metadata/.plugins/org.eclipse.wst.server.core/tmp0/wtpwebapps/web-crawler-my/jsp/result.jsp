<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>

    <title>Home Page</title>
</head>
<body>

<table  border="1" width="50%" cellpadding="5" size="100" >
    <tr>
    <th>URL</th>
  <c:forEach var="term" items="${terms}">
         <th>${term}</th>
   </c:forEach>
    <th>TOTAL</th>
    </tr>
<c:forEach var="item" items="${result.resultIteamDtoList}">
    <tr align= "center">
   
        <td>${item.url}
              <c:forEach var="term" items="${terms}">
                     <td>${item.termCountMap[term]}</td>
               </c:forEach>

        <td>${item.totalCount}</td>
    </tr>
</c:forEach>
</table>
<a href="/web-crawler-my/index.jsp">Main page</a>
</body>
</html>