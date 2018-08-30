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
orderItems = ${storefrontresponse.order.orderItems}
<br/>
<c:forEach items="${storefrontresponse.order.orderItems}" var="orderitem">
  <div>${orderitem}</div>
</c:forEach>
