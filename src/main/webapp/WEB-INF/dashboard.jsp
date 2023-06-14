<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!-- c:out ; c:forEach etc. --> 
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!-- Formatting (dates) --> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/styles.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet">
<meta charset="ISO-8859-1">
<title>Tv Shows</title>
</head>
<body>
    <h1>Tv Shows</h1>
    
	<div class="container">
		<div class="table-1">
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">Show</th>
                        <th scope="col">Network</th>
                        <th scope="col">Rating</th>
                        <th scope="col">Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${shows}" var="show">
                    <tr>
                        <td><a href="/shows/${show.id}">${show.title}</a></td>
                         <td>${show.network}</td>
                        <td>${show.rating}</td>
                        <td><a href="/shows/${show.id}/delete">Delete</a> |
                        <a href="/shows/${show.id}/edit">Edit</a>
                        </td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>

		</div>
        </div>

	<a href="/shows/new">Add a Show</a><br>
	<a href="/logout" class="logout-link">Logout</a>
</body>
</html>