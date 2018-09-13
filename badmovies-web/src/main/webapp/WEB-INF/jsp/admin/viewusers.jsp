<%@ include file="/WEB-INF/jsp/include.jsp" %>

<!DOCTYPE html>
<html>
  <head>
    <title>View Users</title>
  </head>
  
  <body>
    
    <c:choose>
      <c:when test="${not empty users}">
        <table>
          <thead>
            <tr><th>username</th><th>first name</th><th>last name</th></tr>
          </thead>
          <tbody>
            <c:forEach var="user" items="${users}">
              <tr>
                <td><a href="<c:url value='/admin/viewuserdetails?userid=${user.id}'/>">${user.username}</a></td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
              </tr> 
            </c:forEach>
          </tbody>
       </table>
      </c:when>
      <c:otherwise>
        No users are in the database.
      </c:otherwise>
    </c:choose>
  </body>
</html>