<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  


<script>


	var initPage = function(){
		initShoppingCartIcons();
		initMovieInfoDialogs();
	}
	
	function initMovieInfoDialogs(){
		$(".moviebox-poster").each(function(index,value){
			var movieId = $(value).attr('data-movieid');
			$(value).click(function(){
				$.ajax({
					url:"/storefront/moviedetails",
					data: {'movieid':movieId},
					success: function(data){
						var $moviedetailsmodal = $('#moviedetails-modal');
						$moviedetailsmodal.html(data);
						$('#moviedetails-modal').modal('show');
						
					},
					dataType:'html'
				})
			})
		});
	}
	
	
	function initShoppingCartIcons(){
		$(".addtocart").each(function(index,value){
			console.debug("initShoppingCartIcons: found "+$(value));
			$(value).click(function(){
				console.debug(value," was clicked: "+$(value).attr('data-movieid'));
				var movieId = $(value).attr('data-movieid');
				addMovieToShoppingCart(movieId);
			})
		});
	}
	
	function addMovieToShoppingCart(movieId){
		$.ajax({
			  url: "/storefront/rest/updateshoppingcart",
			  data: {'movieid':movieId},
			  success: function(data){
				  console.debug("success!  ",data)
				  $("#shoppingcart-itemcount").text(data.itemCount);
			  },
			  dataType: 'json'
			});
	}
	
	

</script>



	<c:forEach items="${storefrontresponse.movies}" var="movie" varStatus="status">
      <fmt:parseNumber value="${movie.price}" var="mp"/>
      <c:if test="${status.index%6 eq 0}">
         <div class="row">
      </c:if>
      <div class="moviebox-container col-6 col-sm-4 col-md-4 col-lg-4 col-xl-4" data-movieid=${movie.id}>
       <img class="moviebox-poster" alt="" src="${movie.poster}" data-movieid=${movie.id}></img>
       <span class="moviebox-title"><c:out value="${movie.title}"/></span>
       <span class="moviebox-price"><fmt:formatNumber type="currency" value="${mp}"></fmt:formatNumber>  </span>
       <span class="addtocart" data-movieid=${movie.id}>
       <i class="fas fa-plus" title="Add To Cart" data-movieid="${movie.id}"></i>
       </span>
      </div>
      <c:if test="${status.index % 6 eq 5}">
        </div>
      </c:if>
 	</c:forEach>

  
  
  
  <%--modal box for movie details --%>
  <div class="modal fade" id="moviedetails-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">

  </div>
  
  