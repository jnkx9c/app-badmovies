<%@ include file="/WEB-INF/jsp/include.jsp" %>


<script>
	var initPage = function(){
	}
	
</script>

Order History
<div class="container">
  <c:forEach var="order" items="${orders}">
    <div class="row">
      <div class="col col-2">
        <fmt:formatDate value="${order.orderDate}" type="date"/>
      </div>
      <div class="col">
      <a href="orderdetails?orderid=${order.id}"> <c:out value="${order.orderDate}"/> </a> 
      </div>
    </div>
  </c:forEach>
</div>

