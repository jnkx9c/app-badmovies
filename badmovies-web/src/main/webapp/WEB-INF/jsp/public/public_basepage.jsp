<!DOCTYPE html">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  


<!-- https://www.pexels.com/photo/closeup-photo-of-gray-cat-1331821/ -->
<html>
  <head>
    <meta name="viewport" content="width=device-width, user-scalable=no" />
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
  
    <link rel="stylesheet" href="<c:url value="/resources/css/splash.css"/>">
    
    <style>

    <%--#but-register {
      position: absolute;
      width: 300px;
      height: 200px;
      top: 50%;
      left: 50%;
      margin: -100px 0 0 -150px;
    } --%>
    
      
    .navbar-brand img{
      height: 50px;
      width: 50px;
    }   
         
    </style>

  </head>
   
  <body>
   <div class="bg">
    <div class="container">
      <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="#">
          <img src="<c:url value="/resources/images/logo_cat-only.png"/>">
        </a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
    
    
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
          <li class="nav-item ${targetpage=='home.jsp'?'active':''}">
            <a class="nav-link" href="<c:url value='/'/>">Home <span class="sr-only">(current)</span></a>
          </li>        
          <li class="nav-item ${targetpage=='login.jsp'?'active':''}">
            <a class="nav-link" href="<c:url value='/loginpage'/>">Login</a>
          </li>
          <li class="nav-item ${targetpage=='registration.jsp'?'active':''}">
            <a class="nav-link" href="<c:url value='/registrationpage'/>">Register</a>
          </li>
        </ul>
      </div>
    </nav>    
    
    <jsp:include page="${targetpage}"/>

  
  
  </div>
   </div>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
   
  </body>

</html>