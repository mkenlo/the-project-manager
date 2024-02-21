<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %> 
<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Add a new project</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
    <link rel="stylesheet" href="/css/style.css"/>
</head>

<body>

    <%@ include file="navbar.jsp" %>

    <div class="container p-3"> 

        <div class="row">
            <div class="col-md-6 col-sm-12">
        
                <h4 class="mb-3">Create a Project</h4>
                
                
                <form:form action="/projects/new" method="POST" modelAttribute="newProject">
                    <div class="mb-3">
                        <div class="form-floating">
                            <form:input class="form-control" path="title"/>
                            <form:label path="title">Title</form:label>
                        </div>
                        <form:errors path="title" class="errors" />
                    </div>
                    
                    <div class="mb-3">
                        <div class="form-floating">
                            <form:input type="date" class="form-control" id="projectDueDate" path="dueDate"/>
                            <form:label path="title" for="projectDueDate">Due Date</form:label>
                        </div>
                        <form:errors path="dueDate" class="errors" />
                    </div>
                    <div class="mb-3">
                        <div class="form-floating">
                            <form:textarea path="description" class="form-control" style="height:150px"/>
                            <form:label path="description" >Project's description</form:label>
                        </div>
                        <form:errors path="description" class="errors" />
                    </div>
                    <div class="d-flex">
                        <input type="hidden" value="${loggedUser.id}" name="leader">
                        <a href="/dashboard" class="form-control btn btn-outline-danger mx-3">Cancel</a>
                        <input type="submit" class="form-control btn btn-purple mx-3" value="Add">
                    </div>

                </form:form>
            </div>
        </div>

    </div>


</body>
</html>


