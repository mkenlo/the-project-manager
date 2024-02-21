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
                 
        <div class="col-xl-12 grid-margin stretch-card">
            <h4 class="text-purple">Project: ${project.title}</h4>  
            <p class="text-secondary">Project Lead: ${project.leader.firstname}</p> 
            

            <form:form class="" modelAttribute="newTask" method="POST" action="">
                <form:hidden path="creator" value="${loggedUser.id}"/>
                <form:hidden path="project" value="${project.id}"/>
                <form:errors path="name" class="errors" />
                <div class="d-flex">
                    <form:input  path="name" class="form-control" placeholder="Add a task ticket for this project?"/>
                    <button type="submit" class="btn btn-purple mx-2">Add</button>

                </div>
                
                
            </form:form>

            <h5 class="text-purple my-3">Current Tasks</h5> 
            <div class="list-group mt-3 ">
                <c:forEach var="task" items="${tasks}">
                    <div class="list-group-item ">
                        <div class="d-flex w-100 justify-content-between">
                            <label class="mb-1 text-purple">${task.name}</label>
                            <small><fmt:formatDate type ="both" value = "${task.createdOn}" /></small>
                        </div>
                        <p class="mb-1 text-secondary">Added by ${task.creator.firstname}</p>  
                    </div>                
                </c:forEach>
            </div>
        </div>
    </div>

   
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>


