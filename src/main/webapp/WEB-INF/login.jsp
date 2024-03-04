<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %> 
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Login</title>
        <!-- for Bootstrap CSS -->
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
    <link rel="stylesheet" href="/css/style.css"/>
    
</head>
    <body>
        <div class="container bg-gray p-5">
        <div class="row">

            <div class="col-md-9 col-sm-6 p-2">

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
                            <form:input type="text" class="form-control" id="loginUsername" path="username"/>
                            <form:label for="loginUsername" path="username">Username</form:label>
                        </div>
                        <form:errors path="username" class="errors" />                        
                    </div>                    
                    
                    <div class="mb-3">
                        <div class="form-floating">
                            <form:password class="form-control" path="password" id="loginPwd"/>
                            <form:label path="password" for="loginPwd">Password</form:label>
                        </div>
                        <form:errors path="password" class="errors"  />
                    </div>
                    <div>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <input type="submit" class="form-control btn btn-outline-primary" value="Log in">
                        <p class="small"> or <a href="/register">Create an account</a></p>
                    </div>
                </form:form>
            </div>
        </div>
    </div>

        <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
    </body>
</html>