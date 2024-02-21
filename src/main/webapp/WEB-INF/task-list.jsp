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

    <nav class="navbar navbar-expand-lg bg-body-tertiary shadow-sm">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Project Manager</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                <a class="nav-link" aria-current="page" href="/dashboard">Projects</a>
                </li>
                <li class="nav-item">
                <a class="nav-link active" href="/tasks">Tasks</a>
                </li>
                
                
            </ul>
            <form class="d-flex">
                <span class="material-symbols-outlined my-2 ">person</span>
                <label class="px-2 my-2 ">${loggedUser.firstname} ${loggedUser.lastname}</label>
                <a href="/logout" class="btn btn-outline-danger">Logout</a>
            </form>
            </div>
        </div>
    </nav>

    <div class="container p-3">                 
        <div class="col-xl-12 grid-margin stretch-card">
            <h4 class="text-purple">All Tasks</h4>  

            <div class="list-group mt-3 ">
                <c:forEach var="task" items="${tasks}">
                    <div class="list-group-item ">
                        <div class="d-flex w-100 justify-content-between">
                            <label class="mb-1 text-purple">${task.name}</label>
                            <small> Project: ${task.project.title}</small>
                        </div>
                        <p class="mb-1 text-secondary">Added by ${task.creator.firstname} at <fmt:formatDate type ="both" value = "${task.createdOn}" /> </p>  
                    </div>                
                </c:forEach>
            </div>
        </div>
    </div>

   
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>


