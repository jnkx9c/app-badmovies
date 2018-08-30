<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    


<script>
	var initPage = function(){
	}
	
</script>

Order History
<c:forEach var="order" items="${storefrontresponse.orders}">
 <div><a href="orderdetails?orderid=${order.id}"> ${order.id} </a> </div>
</c:forEach>

