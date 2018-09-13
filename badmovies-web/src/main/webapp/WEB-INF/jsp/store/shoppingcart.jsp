<%@ include file="/WEB-INF/jsp/include.jsp" %>



<script>
	var initPage = function(){
		initQuantityUpdates();
	}
	
	function initQuantityUpdates(){
		$('.moviebox').each(function(index,value){
			var movieid = $(value).attr('data-movieid');
			var inpQuantity = $(value).find('input')[0];
			var butUpdate = $(value).find('button.updatecartquantity')[0];
			var butRemove = $(value).find('button.removefromcart')[0];
			
			$(butUpdate).on('click',function(){
				console.debug("inpQuantity = ",$(inpQuantity));
				$.ajax({
					url:'<c:url value="/storefront/rest/updatecartquantity"/>',
					data:{movieid:movieid,quantity:$(inpQuantity).val()},
					dataType:'json',
					success:function(data){
						//update the itemcount in the header
						updateCommonCartFields(data);					}
				})
			});
			
			$(butRemove).on('click',function(){
				$.ajax({
					url:'<c:url value="/storefront/rest/updateshoppingcart"/>',
					data:{movieid:movieid,action:'remove'},
					dataType:'json',
					success:function(data){
						//update the itemcount in the header
						updateCommonCartFields(data);
						//remove the moviebox
						var target = $(value).parent();
						$(target).hide('slow', function(){ $(target).remove(); });
					}
				})
			});
			
			
			
			
		});
	}
	
	function updateCommonCartFields(scdata){
		$('.sc-totalprice').text(scdata.cartTotal);
		$('.sc-itemcount').text(scdata.itemCount);
	}
	
</script>

<style>

  .moviebox{
    display: flex;
    margin-top: 5px;
    margin-bottom: 5px;
  }
  .moviebox .moviebox-poster{
  }
  .moviebox .moviebox-detail{
  }
  
  </style>

  
Subtotal (<span class="sc-itemcount">${sessionScope.shoppingcart.itemCount}</span> items)
  <span class="sc-totalprice">
    ${sessionScope.shoppingcart.formattedCartTotal}
  </span>

<div class="container">
  <c:forEach items="${sessionScope.shoppingcart.itemIdMap}" var="entry">
    <div class="row">
      <div class="moviebox" data-movieid="${entry.key}">
        <img class="moviebox-poster" alt="" src="${entry.value.poster}">    
        <div class="moviebox-detail">
          <div class="moviebox-price">
            <span>Price:</span>
            <span>${entry.value.price}</span>
          </div>
          <div class="moviebox-quantity">
            <span>Quantity:</span>
            <span class="moviebox-quantity">
              <input type="text" size="1" value="${entry.value.quantity}">
              <button type="button" class="btn btn-primary updatecartquantity">Update</button>
              <button type="button" class="btn btn-danger removefromcart">Remove</button>
            </span>
          </div>
        </div>
      </div>
    </div>
  </c:forEach>
</div>


Subtotal (<span class="sc-itemcount">${sessionScope.shoppingcart.itemCount}</span> items)
  <span class="sc-totalprice">
  ${sessionScope.shoppingcart.formattedCartTotal}
</span>

<form action="<c:url value='/storefront/processorder'/>">
  <button type="submit" class="btn btn-primary">Make Purchase</button>
</form>
