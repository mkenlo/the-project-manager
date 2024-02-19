<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %> 
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Hello</title>
        <!-- for Bootstrap CSS -->
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
    <link rel="stylesheet" href="/css/style.css"/>
    
</head>
    <body>
         <div class="container bg-gray p-5">
        <div class="row">
            <h1 class="display-1 text-primary">Project Manager</h1>
            <h2 class="text-primary-emphasis">A place for teams to manage projects</h2>
        </div>
        
        <div class="row">
            <div class="col-md-4 col-sm-6 p-2 mt-3" >
                <h3>Create an account</h3>
                <!--<form:form action="/register" method="POST" modelAttribute="newUser">
                    <div class="mb-3">
                        <div class="form-floating">
                            <form:input cssClass="form-control" id="fullname" path="name">
                            <form:label path="fullname">Name</form:label>
                        </div>
                        <form:errors path="name" class="errors" />
                    </div>
                    <div class="mb-3">
                        <div class="form-floating">
                            <input type="email" class="form-control" id="email" name="email">
                            <label for="email">Email address</label>
                        </div>
                        <form:errors path="email" class="errors" />
                    </div>
                    <div class="mb-3">
                        <div class="form-floating">
                            <input type="password" class="form-control" id="newPassword" name="password">
                            <label for="newPassword">Password</label>
                        </div>
                        <form:errors path="password" class="errors" />
                    </div>
                    <div class="mb-3">
                        <div class="form-floating">
                            <input type="password" class="form-control" id="confirmPassword" name="confirm">
                            <label for="confirmPassword">Confirm Password</label>
                        </div>
                        <form:errors path="confirm" class="errors" />
                    </div>
                    <div>
                        <input type="submit" class="form-control btn btn-outline-primary" value="Register">
                    </div>

                </form:form>-->
            </div>
            <div class="col-md-3"></div>
            <div class="col-md-4 col-sm-6 p-2">

                    <div class="row p-2">
                        <c:if test="${loginRequired!=null}" >
                            <div class="alert alert-danger" role="alert">
                            <h4 class="alert-heading">Oups!</h4>
                                <p>${loginRequired}</p>            
                        </c:if>        
                    </div>

                <h3>Login</h3>
                <!--<form:form action="/login" method="POST" modelAttribute="newLogin">               
                    <div class="mb-3">
                        <div class="form-floating">
                            <input type="email" class="form-control" id="loginEmail" name="email">
                            <label for="loginEmail">Email address</label>
                        </div>
                        <form:errors path="email" class="errors" />                        
                    </div>                    
                    
                    <div class="mb-3">
                        <div class="form-floating">
                            <input type="password" class="form-control" id="loginPassword" name="password">
                            <label for="loginPassword">Password</label>
                        </div>
                        <form:errors path="password" class="errors"  />
                    </div>
                    <div>
                        <input type="submit" class="form-control btn btn-outline-primary" value="Log in">
                    </div>
                </form:form>-->
            </div>
        </div>
    </div>

        <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
    </body>
</html>