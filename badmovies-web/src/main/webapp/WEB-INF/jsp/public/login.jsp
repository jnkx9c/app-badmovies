<%@ include file="/WEB-INF/jsp/include.jsp" %>
 

<ul>
  <li>Prompt for user name and password.</li>
  <li>Show Appropriate error messaging if the user name and password are incorrect </li>
</ul>

    <c:if test="${param.registrationsuccess eq 'true'}">
        <div  class="alert alert-success" role="alert">
            Thanks for registering, <c:out value="${firstname}"/>.  You have successfully completed the Registration process.<br/>
            Now, login to our site!
        </div>
    </c:if>

  <div class="formdata">    
    <form action="<c:url value='login'/>" method="post">
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
           The email address or password is incorrect. Try again.
        </div>
    </c:if>
    <button type="submit" class="btn btn-primary" name="submit">Submit</button>
   </form>
 </div> 


