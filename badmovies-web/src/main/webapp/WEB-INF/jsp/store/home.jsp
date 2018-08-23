<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
</head>
<body>
Store Front

Movies = <c:out value="${movies}"></c:out>
<table>
	<c:forEach items="${movies}" var="movie">
		<tr><td>Title</td><td><c:out value="${movie.title}"/></td></tr>
	
	</c:forEach>

</table>

</body>
</html>