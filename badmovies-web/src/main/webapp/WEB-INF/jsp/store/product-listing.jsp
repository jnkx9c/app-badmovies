<%@ include file="/WEB-INF/jsp/include.jsp" %>
  


<script>
	var currentpage = ${page};
	var colsPerRow = 6;
	var initPage = function(){
		initMovieContainers();
		initDetailsModal();
	}
	
	function initDetailsModal(){
		//when modal is shown, bind the shoppingcart icon to a click handler.
		$('#moviedetails-modal').on('shown.bs.modal', function() {
	    	var movieId = $('#moviedetails-modal div.modal-content').attr('data-movieid');
	    	$('#moviedetails-modal span.moviebox-shoppingcart').on('click',function(){
	    		toggleShoppingCart(movieId);
	    	})
	    });
		
		//when modal is hidden, remove the click handler binding. 
		//(not required, but a good practice to clean up after yourself)
		$('#moviedetails-modal').on('hidden.bs.modal', function() {
	    	$('#moviedetails-modal span.moviebox-shoppingcart').off('click');
	    });		
	}
	
	
	//call the movielist service, iterate through the list of returned movies
	//creating and populating a movie div for each instance.	
	function initMovieContainers(){
		console.log('---------initMovieContainers--------');
		var moviesection = $('#moviesection')[0];
		$.ajax({
			url:'<c:url value="/storefront/rest/movielist"/>',
			data:{'page':currentpage},
			success: function(movielistdto){
				console.debug(movielistdto);
				$.each(movielistdto.movies,function(i,movie){
					console.debug('movie = ',movie.id);
					var addStartRowDiv = i%colsPerRow==0?true:false;
					
					if(addStartRowDiv){
						$(moviesection).append('<div class="row"></div>');
					}
					
					var $rowDiv = $(moviesection).find('.row').last();
					
					var $movieBoxDiv = $('<div class="moviebox-container col-6 col-sm-4 col-md-4 col-lg-4 col-xl-2 mb-3"><div>');
					$movieBoxDiv.attr('data-movieid',movie.movieId);
					
					//add the poster
					var $movieBoxPoster = $('<img class="moviebox-poster" alt=""></img>');
					$movieBoxPoster.attr('src',movie.poster);
					$movieBoxPoster.click(function(){
						$.ajax({
							url:'<c:url value="/storefront/moviedetails"/>',
							data: {'movieid':movie.movieId},
							success: function(data){
								var $moviedetailsmodal = $('#moviedetails-modal');
								$moviedetailsmodal.html(data);
								$('#moviedetails-modal').modal('show');
								
							},
							dataType:'html'
						})
					});
					$movieBoxDiv.append($movieBoxPoster);
					
					//add the title
					var $movieBoxTitle = $('<span class="moviebox-title">'+movie.title+'</span>');
					$movieBoxDiv.append($movieBoxTitle);
					
					
					//add the price
					$movieBoxDiv.append('<span class="moviebox-price">'+movie.formattedPrice+'</span>');
					
					//add the shoppingcart span
					var $movieBoxCartSpan = $('<span class="moviebox-shoppingcart"></span>');
					if(movie.inCart){
						$movieBoxCartSpan.addClass('text-success');
					}
					$movieBoxCartSpan.click(function(){
						toggleShoppingCart(movie.movieId);
					});
					var $movieBoxCartIcon = $('<i class="fas fa-shopping-cart fa-xs"></i>');
					$movieBoxCartSpan.append($movieBoxCartIcon);
					$movieBoxDiv.append($movieBoxCartSpan);
	
					
					$rowDiv.append($movieBoxDiv);
				
				});
				

				initPaginator(movielistdto);
				
			},
			dataType:'json'
		});
		

	}
	
	
	function initPaginator(movielistdto){
		//set up the paginator
		var productsURL = '<c:url value="/storefront/products"/>';
		var $paginatorUL = $("ul.pagination");
		var $pageLI = $('<li class="page-item"><a class="page-link" href="#">Previous</a></li>');
		if(currentpage==0){
			$pageLI.addClass('disabled');
		}else{
			$pageLI.find('a').attr('href','<c:url value="/storefront/products"/>?${AppConstants.PARAM_PAGE}='+(currentpage-1));
		}
		$paginatorUL.append($pageLI);

		for(i=0; i<movielistdto.totalPages; i++){
			$pageLI = $('<li class="page-item"><a class="page-link" href="'+productsURL+'?${AppConstants.PARAM_PAGE}='+i+'">'+(i+1)+'</a></li>');
			if(currentpage == i){
				$pageLI.addClass('active');
			}
			$paginatorUL.append($pageLI);
		}		
		$paginatorUL.append('<li class="page-item"><a class="page-link" href="'+productsURL+'?${AppConstants.PARAM_PAGE}='+(movielistdto.totalPages-1)+'">Last</a></li>');

	}

	
	/*
	   a generic function to 'toggle' a movie into and out of the shopping cart.
	   The state of the movie/shopping-cart relationship is determined by checking if the movie has a particular css class.
	*/
	function toggleShoppingCart(movieId){
		var inCart = $(".moviebox-container[data-movieid="+movieId+"] span.moviebox-shoppingcart").hasClass('text-success');
		if(inCart){
			removeMovieFromShoppingCart(movieId);
		}else{
			addMovieToShoppingCart(movieId);
		}
	}
	
	
	/*
	  adds a movie to the shopping cart on the server side, 
	  then updates the css to reflect that the movie is in the shopping cart.
	*/
	function addMovieToShoppingCart(movieId){
		updateShoppingChart('add',movieId);
		//change the shoppingcart icon
		$(".moviebox-container[data-movieid="+movieId+"] span.moviebox-shoppingcart").addClass('text-success');
		//update the icon on the details modal
		$("#moviedetails-modal span.moviebox-shoppingcart").addClass('text-success');
	}
	
	/*
	  removes a movie from the shopping cart on the server side, 
	  then updates the css to reflect that the movie is NOT in the shopping cart.
	*/
	function removeMovieFromShoppingCart(movieId){
		updateShoppingChart('remove',movieId);	
		$(".moviebox-container[data-movieid="+movieId+"] span.moviebox-shoppingcart").removeClass('text-success');
		//update the icon on the details modal
		$("#moviedetails-modal span.moviebox-shoppingcart").removeClass('text-success');
	}	
	

	/*
		makes an ajax call to update the shopping cart.
		valid 'action' parameters are:
			add
			remove
	*/
	function updateShoppingChart(action,movieId){
		$.ajax({
		  url: "<c:url value='/storefront/rest/updateshoppingcart'/>",
		  data: {'${AppConstants.PARAM_ACTION}':action,'${AppConstants.PARAM_MOVIE_ID}':movieId},
		  success: function(data){
			  $("#shoppingcart-itemcount").text(data.itemCount);
		  },
		  dataType: 'json'
		});	
	}
	
	

	
	
	

</script>
  <nav>
    <ul class="pagination"></ul>
  </nav>
  <div id="moviesection" class="container"></div>
  <nav>
    <ul class="pagination"></ul>
  </nav>
  <%--modal box for movie details --%>
  <div class="modal fade" id="moviedetails-modal" tabindex="-1" role="dialog"></div>
  
  