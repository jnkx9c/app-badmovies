<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    


<script>
	var initPage = function(){
	}
	
</script>
Order Details

${storefrontresponse.order.id}<br/>
<br/>

<div class="container">
  <div class="row">
    <span>Order Date:</span>
    <c:out value="${storefrontresponse.order.orderDate}"/>
  </div>
  <div class="row">
    <span>Order Price:</span>
    <c:out value="${storefrontresponse.order.totalOrderPrice}"/>
  </div>
  <div class="row">
    <span>Total Items:</span>
    <c:out value="${storefrontresponse.order.totalOrderQuantity}"/>
  </div>
<c:forEach items="${storefrontresponse.order.orderItems}" var="orderitem">
  <div class="row">
  ${orderitem.movie.title}
  </div>
</c:forEach>
</div>