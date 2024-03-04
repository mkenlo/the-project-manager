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
    <div class="container">
        <div class="jumbotron mt-3">
            <h1 class="display-1 text-primary">Project Manager</h1>
            <h2 class="text-primary-emphasis mb-3">A place for teams to manage projects</h2>
            <p class="lead">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor
                incididunt ut labore et dolore magna
                aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea
                commodo consequat.</p>
            <a class="btn btn-lg btn-primary" href="/login" role="button">Get Started</a>
        </div>
    </div>

        <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
    </body>
</html>