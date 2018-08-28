<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  


    <style>

 
    #buttons{
      position: absolute;
      width: 150px;
      height: 125px;
      top: 50%;
      left: 50%;
      margin: -65px 0 0 -75px;
    }
    
    #buttons .btn{
      width: 100%;
    }
 
<%--  
   #but-register {
      position: absolute;
      width: 150px;
      height: 50px;
      top: 50%;
      left: 50%;
      margin: -25px 0 0 -75px;
    } 
 --%>
         
    </style>

 
    

    <span>Your cat likes movies too.</span>
    <div id="buttons">
      <a href="<c:url value='/registrationpage'/>" id="but-register" class="btn btn-primary btn-lg">Register</a>
      <div style="text-align: center;">or</div>
      <a href="<c:url value='/loginpage'/>" id="but-login" class="btn btn-primary btn-lg">Login</a>
    </div>
 