<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
<%@ page isErrorPage="true" %> 
<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Dashboard</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
    <link rel="stylesheet" href="/css/style.css"/>
</head>

<body>

    <%@ include file="navbar.jsp" %>

    <div class="container p-3">        
        <div class="row mb-3">
            <div class="col-xl-12 grid-margin stretch-card">
                <div class="card">
                    <div class="card-header d-flex justify-content-between">
                        <h4 class="card-title text-purple">All Projects </h4>
                        <a href="/projects/new" class="btn btn-purple">+ new project</a>
                        </div>
                    <div class="card-body">     
                        <div class="table-responsive">
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th scope="col"> # </th>
                                        <th scope="col"> Project</th>
                                        <th scope="col"> Team Lead</th>
                                        <th scope="col"> Due Date </th>
                                        <th scope="col"> Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="item" items="${projects}">
                                        <tr>
                                            <!--<c:if test="${item.leader.id!=loggedUser.id}">-->
                                                <td>${item.id}</td>
                                                <td><a href="/projects/${item.id}">${item.title}</a></td>
                                                <td>${item.leader.firstname} ${item.leader.lastname}</td>
                                                <td><fmt:formatDate type ="date" value = "${item.dueDate}" /></td>
                                                <td><a href="/projects/${item.id}/join/${loggedUser.id}">Join team</a> </td>   
                                            <!--</c:if> -->                                   
                                        
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>  <!-- end of card-->
            </div>
            
        </div>

        <div class="row mb-3">
            <div class="col-xl-12 grid-margin stretch-card">
                <div class="card">
                    <div class="card-header">
                        <h4 class="card-title text-purple">My Projects </h4>
                    </div>
                    <div class="card-body">      
                        <div class="table-responsive">
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th scope="col"> # </th>
                                        <th scope="col"> Project</th>
                                        <th scope="col"> Team Lead</th>
                                        <th scope="col"> Due Date </th>
                                        <th scope="col"> Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="item" items="${loggedUser.assignedProjects}">
                                        <tr>                                            
                                            <td>${item.id}</td>
                                            <td><a href="/projects/${item.id}">${item.title}</a></td>
                                            <td>${item.leader.firstname} ${item.leader.lastname}</td>
                                            <td><fmt:formatDate type ="date" value = "${item.dueDate}" /></td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${item.leader.id==loggedUser.id}">
                                                        <a href="/projects/${item.id}/edit">Edit</a> 
                                                    </c:when>
                                                    <c:otherwise> 
                                                        <a href="/projects/${item.id}/leave/${loggedUser.id}">Leave Team</a> 
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>                                               
                                        </tr>
                                    </c:forEach>

                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>  <!-- end of card-->
            </div>
            
        </div>
    

    </div><!--end of container-->
    


    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</body>

</html>