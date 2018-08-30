<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  


<script>
	var initPage = function(){
	}
	
</script>

Order History
<div class="container">
<c:forEach var="order" items="${storefrontresponse.orders}">
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

