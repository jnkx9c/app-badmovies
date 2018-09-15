<%@ include file="/WEB-INF/jsp/include.jsp" %>
    

    <div class="modal-dialog" role="document">

    <div class="modal-content" data-movieid="${movie.movieId}">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">${movie.title}</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <img src="${movie.poster}"/>
        <span class="moviebox-shoppingcart ${movie.inCart?'text-success':'' }" >
          <i class="fas fa-shopping-cart fa-xs "></i>
        </span>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
    </div>
