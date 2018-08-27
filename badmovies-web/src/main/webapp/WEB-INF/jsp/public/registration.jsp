<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<ul>
  <li>If the user does not have a login yet, allow the user to register.</li>
  <li>Prompt for email address (this will be the login id), password, repeat password, first name, last name, address, city, state, and zipcode.</li>
  <li>All fields are required</li>
</ul>
<style>
  .registrationfielderror{
    padding: 0;
  }

</style>

<c:set var="Efirstname" value="${bindingresult.hasFieldErrors('firstname')}"/>
<c:set var="Efirstname" value="${bindingresult.getFieldErrors('firstname')}"/>



  <div class="formdata">   
  <form:form method="post" action="register" modelAttribute="registrationform"> 
              
      <div class="row">
        <div class="col">
          <div class="form-group">            
            <form:label path="firstname" cssErrorClass="registrationfielderror alert alert-danger">First Name</form:label>
            <form:errors path="firstname" cssClass="registrationfielderror alert alert-danger" />  
            <form:input path="firstname" type="text" class="form-control ${bindingresult.hasFieldErrors('firstname')?'border border-danger':''}" placeholder="First Name" required="required"/>
          </div>
        </div>
        <div class="col">
          <div class="form-group">
            <form:label path="lastname" cssErrorClass="registrationfielderror alert alert-danger">Last Name</form:label>
            <form:errors path="lastname" cssClass="registrationfielderror alert alert-danger" />  
            <form:input path="lastname" type="text" class="form-control ${bindingresult.hasFieldErrors('lastname')?'border border-danger':''}" placeholder="Last Name" required="required"/>
          </div>
        </div>
      </div>
      
      <div class="row">
        <div class="col">
          <div class="form-group">
            <form:label path="address" cssErrorClass="registrationfielderror alert alert-danger">Address</form:label>
            <form:errors path="address" cssClass="registrationfielderror alert alert-danger" />  
            <form:input path="address" type="text" class="form-control ${bindingresult.hasFieldErrors('address')?'border border-danger':''}" placeholder="Address" required="required"/>
          </div>  
        </div>
        <div class="col">
          <div class="form-group">
            <form:label path="city" cssErrorClass="registrationfielderror alert alert-danger">City</form:label>
            <form:errors path="city" cssClass="registrationfielderror alert alert-danger" />  
            <form:input path="city" type="text" class="form-control ${bindingresult.hasFieldErrors('city')?'border border-danger':''}" placeholder="City" required="required"/>
          </div>  
        </div>
      </div>
      
      <div class="row">
        <div class="col">
          <div class="form-group">
            <form:label path="state" cssErrorClass="registrationfielderror alert alert-danger">State</form:label>
            <form:errors path="state" cssClass="registrationfielderror alert alert-danger" />  
            <form:select path="state" items="${states}" cssClass="form-control" />

          </div>  
        </div>
        <div class="col">
          <div class="form-group">
            <form:label path="zip" cssErrorClass="registrationfielderror alert alert-danger">Zip</form:label>
            <form:errors path="zip" cssClass="registrationfielderror alert alert-danger"/> 
            <form:input path="zip" type="text" class="form-control ${bindingresult.hasFieldErrors('zip')?'border border-danger':''}" placeholder="Zip" required="required"/>
          </div>  
        </div>
      </div>      
      
      
      <div class="row">
        <div class="col">
          <div class="form-group">
            <form:label path="email" cssErrorClass="registrationfielderror alert alert-danger">Email Address</form:label>
            <form:errors path="email" cssClass="registrationfielderror alert alert-danger"/> 
            <c:if test="${error_userexists}">
              <span class="registrationfielderror alert alert-danger">Email address is taken.</span>
            </c:if>
            <form:input path="email" type="email" class="form-control ${bindingresult.hasFieldErrors('email')?'border border-danger':''}" aria-describedby="emailHelp" placeholder="Email" required="required"/>
            <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
          </div>
        </div>
      </div>
      
      <div class="row">
        <div class="col-12 col-sm-12 col-md-6 col-lg-6 col-xl-6">
          <div class="form-group">
          <form:label path="password1" cssErrorClass="registrationfielderror alert alert-danger">Password</form:label>
          <form:errors path="password1" cssClass="registrationfielderror alert alert-danger"/> 
          <form:input path="password1" type="password" class="form-control ${error_passwordmatching or bindingresult.hasFieldErrors('password1')?'border border-danger':''}" placeholder="Password" required="required"/>
          </div>
        </div>
        <div class=" col-12 col-sm-12 col-md-6 col-lg-6 col-xl-6">
          <div class="form-group">
            <form:label path="password2" cssErrorClass="registrationfielderror alert alert-danger" cssStyle="white-space:nowrap">Retype Password</form:label>
            <c:if test="${error_passwordmatching}">
              <span class="registrationfielderror alert alert-danger">Passwords do not match.</span>
            </c:if>
            <form:input path="password2" type="password" class="form-control ${error_passwordmatching?'border border-danger':''}" placeholder="Retype Password" required="required"/>
          </div>     
        </div>
     </div>
  
    <button type="submit" class="btn btn-primary" name="submit">Register</button>
   </form:form>
 </div>