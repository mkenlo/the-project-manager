<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
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
        <div class="card">
            <div class="card-header">
                <h4 class="card-title text-purple">Project Details</h4>            
            </div>
            <div class="card-body">
                <div class="row">   
                    <div class="col-md-3"><p class="fw-semibold">Project Title</p></div>
                    <div class="col-md-9"><p>${project.title}</p></div> 
                </div>
                <div class="row">   
                    <div class="col-md-3"><p class="fw-semibold">Project Description</p></div>
                    <div class="col-md-9"><p>${project.description}</p></div> 
                </div>
                <div class="row">   
                    <div class="col-md-3"><p class="fw-semibold">Due Date</p></div>
                    <div class="col-md-9"><p><fmt:formatDate type ="date" value = "${project.dueDate}" /></p></div> 
                </div>
                <div class="row">   
                    <div class="col-md-3"><p class="fw-semibold">Project's Members <span class="badge text-bg-info">${project.assignedUsers.size}</span></p></div>
                    <div class="col-md-9">
                        <p>
                            <c:forEach var="person" items="${project.assignedUsers}">
                                ${person.firstname} ${person.lastname},                      
                            </c:forEach>
                        </p>
                    </div> 
                </div>
            
            </div>
            <div class="card-footer d-flex justify-content-between">
                <a href="/projects/${project.id}/tasks" class="btn btn-outline-primary">See tasks</a>
                <c:if test="${loggedUser.id == project.leader.id}"> 
                    <div>
                        <a href="/projects/${project.id}/edit" class="btn btn-primary">Edit</a>            
                        <a href="/projects/${project.id}/delete" class="btn btn-danger">Delete</a>  
                    </div>              
                </c:if>
            
            <div>
        </div> 





        

    </div>


</body>
</html>


