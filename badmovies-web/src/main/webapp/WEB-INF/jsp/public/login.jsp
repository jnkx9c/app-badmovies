<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  

<ul>
  <li>Prompt for user name and password.</li>
  <li>Show Appropriate error messaging if the user name and password are incorrect </li>
</ul>

    <c:if test="${param.registrationsuccess eq 'true'}">
        <div  class="alert alert-success" role="alert">
            Thanks for registering, <c:out value="${firstname}"/>.  You have successfully completed the Registration process.<br/>
            Now, log into our site!
        </div>
    </c:if>

  <div class="formdata">    
    <form action="login" method="post">
      <div class="form-group">
        <label for="exampleInputEmail1">Email Address</label>
        <input type="email" class="form-control" id="exampleInputEmail1" name="username" aria-describedby="emailHelp" placeholder="Enter email">
        <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
      </div>
      <div class="form-group">
        <label for="exampleInputPassword1">Password</label>
        <input type="password" class="form-control" id="exampleInputPassword1" name="password" placeholder="Password">
     </div>
    <c:if test="${param.error != null}">
        <div id="error" class="alert alert-danger" role="alert">
            Bad Credentials
        </div>
    </c:if>
    <button type="submit" class="btn btn-primary" name="submit">Submit</button>
   </form>
 </div> 


