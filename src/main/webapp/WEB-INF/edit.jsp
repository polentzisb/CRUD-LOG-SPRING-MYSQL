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
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/styles.css">
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <title>Edit Show</title>
</head>
<body>
    <div class="center-card">
        <div class="col-sm-6">
            <div class="card">
                <div class="card-body">
                    <p class="card-text">
                        <h1>Edit Show</h1>
                        <form action="/shows/${show.id}/edit" method="POST">
                            <label for="title">Show:</label>
                            <input type="text" id="title" name="title" value="${show.title}" required>
                            <br><br>
                            <label for="network">Network:</label>
                            <input type="text" id="network" name="network" value="${show.network}" required>
                            <br><br>
                            <label for="rating">Rating:</label>
                            <input type="number" id="rating" name="rating" value="${show.rating}" min="1" max="10" required>
                            <br><br>
                            <input type="submit" value="Update Show"><br><br>
                            <a href="/shows/${show.id}/delete">Delete</a>
                        </form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
