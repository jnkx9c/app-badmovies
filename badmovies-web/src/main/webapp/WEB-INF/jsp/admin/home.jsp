<%@ include file="/WEB-INF/jsp/include.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
</head>
<body>
Hey
	<form action="admin/importmovie">
		<input type="text" name="imdbMovieId"/>
		<button type="submit">Submit</button>

	</form>
<table>
  <tr><td><a href="<c:url value='/admin/viewusers'/>">View Users</a></td></tr>
</table>

</body>
</html>