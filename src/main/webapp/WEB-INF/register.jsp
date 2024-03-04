<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %> 
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Register</title>
        <!-- for Bootstrap CSS -->
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
    <link rel="stylesheet" href="/css/style.css"/>
    
</head>
    <body>
         <div class="container bg-gray p-5">
        <div class="row">
            <div class="col-md-9 col-sm-6 p-2 mt-3" >
                <h3>Create an account</h3>
                `<form:form action="/register" method="POST" modelAttribute="newUser">
                    <div class="mb-3">
                        <div class="form-floating">
                            <form:input cssClass="form-control" path="firstname"/>
                            <form:label path="firstname">First Name</form:label>
                        </div>
                        <form:errors path="firstname" class="errors" />
                    </div>
                    <div class="mb-3">
                        <div class="form-floating">
                            <form:input cssClass="form-control" path="lastname"/>
                            <form:label path="lastname">Last Name</form:label>
                        </div>
                        <form:errors path="lastname" class="errors" />
                    </div>
                    <div class="mb-3">
                        <div class="form-floating">
                            <form:input cssClass="form-control" path="username"/>
                            <form:label path="username">Username</form:label>
                        </div>
                        <form:errors path="username" class="errors" />
                    </div>
                    <div class="mb-3">
                        <div class="form-floating">
                            <form:input type="email" class="form-control" id="userEmail" path="email"/>
                            <form:label for="userEmail" path="email">Email address</form:label>
                        </div>
                        <form:errors path="email" class="errors" />
                    </div>
                    <div class="mb-3">
                        <div class="form-floating">
                            <form:password class="form-control" path="password"/>
                            <form:label path="password">Password</form:label>
                        </div>
                        <form:errors path="password" class="errors" />
                    </div>
                    <div class="mb-3">
                        <div class="form-floating">
                            <form:password class="form-control" path="confirmPassword"/>
                            <form:label path="confirmPassword">Confirm Password</form:label>
                        </div>
                        <form:errors path="confirmPassword" class="errors" />
                    </div>
                    <div>
                        <input type="submit" class="form-control btn btn-outline-primary" value="Register">
                        <p class="small"> or <a href="/login">Login</a></p>

                    </div>

                </form:form>
            </div>
            
        </div>
    </div>

        <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
    </body>
</html>