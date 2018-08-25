<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <meta name="viewport" content="width=device-width, user-scalable=no" />

<title>Basepage</title>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<%-- 
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous">
--%>
<script defer src="https://use.fontawesome.com/releases/v5.2.0/js/all.js" integrity="sha384-4oV5EgaV02iISL2ban6c/RmotsABqE4yZxZLcYMAdG7FAPsyHYAPpywE9PJo+Khy" crossorigin="anonymous"></script>



<link rel="stylesheet" href="/resources/css/storefront.css"/>


</head>
<body>

  <div class="container movie-maincontainer">
    <nav class="navbar navbar-dark bg-primary navbar-inverse">
      THis is my navbar!!!   number of items in the shopping cart are <c:out value="${storefrontresponse.shoppingCart.itemCount}"/>
        
        <div class="fa-4x">
        <a href="shoppingcart">
          
            <span class="fa-layers fa-fw">
              <i class="fas fa-shopping-cart"></i>
              <span id="shoppingcart-itemcount" class="fa-layers-counter" style="background:Tomato"><c:out value="${storefrontresponse.shoppingCart.itemCount}"/></span>
            </span>
          
        </a>
        </div>
    </nav>
    <div class="movie-maincontent">
      <jsp:include page="${storefrontresponse.view}.jsp"></jsp:include> 
    </div>

  </div>

<script  src="http://code.jquery.com/jquery-3.3.1.min.js"  integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="  crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

<script>
  $(document).ready(function(){
	  if (typeof initPage == 'function') { 
		  initPage(); 
		} 
  });
  </script>


</body>
</html>