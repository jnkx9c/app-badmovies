package org.kilgore.badmovies.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.kilgore.badmovies.entity.Movie;
import org.springframework.format.number.CurrencyStyleFormatter;


public class ShoppingCart implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Map<Integer, ShoppingCartItem> itemIdMap = new HashMap<>();
	private float cartTotal = 0f;
	private int itemCount = 0;
	private String formattedCartTotal = null;
	
	

	public boolean containsItem(Integer itemId) {
		return itemIdMap.containsKey(itemId);
	}

	public void addItem(Movie movie) {
		if(movie!=null && movie.getId() !=null && !itemIdMap.containsKey(movie.getId())) {
			ShoppingCartItem item = new ShoppingCartItem();
			item.setItemId(movie.getId());
			item.setPoster(movie.getPoster());
			item.setTitle(movie.getTitle());
			item.setPrice(movie.getPrice()!=null?movie.getPrice():0f);
			item.setQuantity(1);
			itemIdMap.put(item.getItemId(), item);

		}
		calculateTotal();
	}

	public void removeItem(Integer movieid) {
		if(movieid!=null && itemIdMap.containsKey(movieid)) {
			itemIdMap.remove(movieid);
		}
		calculateTotal();
		
	}

	public Set<Integer> getItemIds() {
		return itemIdMap.keySet();
	}

	public Map<Integer, ShoppingCartItem> getItemIdMap() {
		return itemIdMap;
	}

	public void updateQuantity(Integer itemid, Integer quantity) {
		if(itemid!=null && quantity!=null) {
			ShoppingCartItem item = itemIdMap.get(itemid);
			if(item !=null) {
				item.setQuantity(quantity.intValue());
			}
		}
		calculateTotal();
	}

	public void calculateTotal() {
		float _cartTotal = 0f;
		int _totalItems = 0;
		for(Entry<Integer, ShoppingCartItem> entry: itemIdMap.entrySet()) {
			ShoppingCartItem item = entry.getValue();
			_cartTotal += item.getPrice()*item.getQuantity();
			_totalItems+=item.getQuantity();
		}
		cartTotal=_cartTotal;
		itemCount=_totalItems;
		formatCartTotal();
	}
	
	private void formatCartTotal() {
		formattedCartTotal = NumberFormat.getCurrencyInstance().format(cartTotal);
	}
	
	
	


	public float getCartTotal() {
		return cartTotal;
	}



	public int getItemCount() {
		return itemCount;
	}

	public String getFormattedCartTotal() {
		return formattedCartTotal;
	}

	public void clear() {
		itemIdMap.clear();
		calculateTotal();
	}



}
