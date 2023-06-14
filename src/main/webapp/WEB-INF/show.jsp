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
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/styles.css">
    <title>Shows</title>
</head>
<body>
    <div class="center-card">
        <div class="col-sm-6">
            <div class="card text-center">
                <div class="card-header">
                    <h2>${show.title}</h2><br>
                </div>
                <div class="card-body">
                    <h5 class="card-title">Network: ${show.network}</h5>
                    <p class="card-text">Rating: ${show.rating}</p>
                    <a href="/shows/${show.id}/edit" class="btn btn-primary">Edit</a>
                </div>
            </div>
        </div>
    </div>
</body>
</html>