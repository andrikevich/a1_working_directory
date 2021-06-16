<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>

<html>

<head>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">



    <link type="text/css" rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/copier.css" />

    <title>SimCard chooser</title>

</head>

<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/dtgroup/dt-main-page">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Link</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
                </li>
            </ul>
            <form:form name="simCardSelection" action="${pageContext.request.contextPath}/dtgroup/selectedSims" method="GET" class="d-flex">
                <input name="searchParam" class="form-control me-2" type="search" placeholder="Search" aria-label="Search simCard">
                <button class="btn btn-outline-success" type="submit">Search</button>
            </form:form>
        </div>
        <div id="loginInfo">
            <security:authorize access="isAuthenticated()">
                <security:authentication property="principal.username" var="username" />
                You are login as "<span style="font-size: 130%; color: #ff0000;text-transform: uppercase;"><b >  ${username}  </b> </span>"
                <br>
                <a href="${pageContext.request.contextPath}/showMyLoginPage?logout">Logout</a>
            </security:authorize>
        </div>
    </div>
</nav>


