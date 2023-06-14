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
    <meta charset="ISO-8859-1">
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/styles.css">
    <title>Create a Show</title>
</head>
<body>
    <div class="center-card">
        <div class="col-sm-6">
            <div class="card">
                <div class="card-body">
                    <h1>Create a New Show</h1><br>
                    <form:form action="/shows/new" method="POST" modelAttribute="show">
                        <div class="row">
                            <div class="col-md-6 mb-1">
                                <form:label path="title">Show:</form:label>     
                                <form:input path="title" /><br><br>
                                <form:errors path="title"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <form:label path="network">Network:</form:label>
                                <form:input path="network" />
                                <form:errors path="network"/><br><br><br>
                            </div>
                            <div class="col-md-6 mb-3">
                                <form:label path="rating">Rating (1-5):</form:label>
                                <form:input type="number" min="1" max="5" path="rating" /><br><br><br>
                                <form:errors path="rating"/>
                            </div>
                            <input type="submit" value="Create"/>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>