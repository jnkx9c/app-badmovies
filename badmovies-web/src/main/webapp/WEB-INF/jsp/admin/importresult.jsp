<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Import Result</title>
</head>
<body>


Movie = <c:out value="${movie}"></c:out>

<table>
	<tr><td>Title</td><td><c:out value="${movie.title}"/></td></tr>
	<tr><td colspan="2">Ratings</td></tr>
	<c:forEach items="${movie.ratings}" var="rating">
		<tr><td><c:out value="${rating.source}"/></td><td><c:out value="${rating.value}"/></td></tr>
	</c:forEach>
	<tr><td><img alt="" src="${movie.poster}">  </td></tr>
	
	


</table>





</body>
</html>