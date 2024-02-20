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
                            <input type="email" class="form-control" for="userEmail" name="email"/>
                            <label for="userEmail">Email address</label>
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
                    </div>

                </form:form>
            </div>
            <div class="col-md-3"></div>
            <div class="col-md-4 col-sm-6 p-2">

                    <div class="row p-2">
                        <c:if test="${error!=null}" >
                            <div class="alert alert-danger" role="alert">
                            <h4 class="alert-heading">Oups!</h4>
                                <p>${error}</p>            
                        </c:if>        
                    </div>

                <h3>Login</h3>
                <form:form action="/login" method="POST" modelAttribute="loginUser">               
                    <div class="mb-3">
                        <div class="form-floating">
                            <input type="email" class="form-control" id="loginEmail" name="email">
                            <label for="loginEmail">Email address</label>
                        </div>
                        <form:errors path="email" class="errors" />                        
                    </div>                    
                    
                    <div class="mb-3">
                        <div class="form-floating">
                            <form:password class="form-control" path="password"/>
                            <form:label path="password">Password</form:label>
                        </div>
                        <form:errors path="password" class="errors"  />
                    </div>
                    <div>
                        <input type="submit" class="form-control btn btn-outline-primary" value="Log in">
                    </div>
                </form:form>
            </div>
        </div>
    </div>

        <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
    </body>
</html>