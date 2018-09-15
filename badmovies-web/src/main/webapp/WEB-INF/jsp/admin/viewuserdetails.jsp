<%@ include file="/WEB-INF/jsp/include.jsp" %>

<!DOCTYPE html>
<html>
  <head>
    <title>User Details</title>
  </head>
  
  <body>
    
    ${user.username}
    
    <hr/>
    <c:choose>
      <c:when test="${not empty orders }">
        <table>
          <thead>
            <tr><th>order date</th><th>order price</th><th>order quantity</th></tr>
          </thead>
          <tbody>
            <c:forEach var="order" items="${orders}">
              <tr><td>${order.orderDate}</td><td>${order.totalOrderPrice }</td><td>${order.totalOrderQuantity}</td></tr>
            </c:forEach>
          </tbody>        
        
        
        </table>
      </c:when>
      <c:otherwise>
        No Orders
      </c:otherwise>
    
    
    
    </c:choose>
    
  </body>
</html>