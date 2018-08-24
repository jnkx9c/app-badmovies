<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    



  <div class="container">
	<c:forEach items="${movies}" var="movie" varStatus="status">
      <c:if test="${status.index%6 eq 0}">
        <div class="row">
      </c:if>
      <div class="moviecontainer col-6 col-sm-4 col-md-4 col-lg-3 col-xl-3">
       <img class="moviebox-poster" alt="" src="${movie.poster}"></img>
       <div class="moviebox-title"><c:out value="${movie.title}"/></div>
      </div>
      <c:if test="${status.index % 6 eq 5}">
        </div>
      </c:if>
      
      
      
	</c:forEach>
  </div>
