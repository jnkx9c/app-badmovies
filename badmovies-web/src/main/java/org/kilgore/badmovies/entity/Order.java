package org.kilgore.badmovies.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Orders")
public class Order extends BaseEntityObject{

	@ManyToOne
	private User user;
	@OneToMany(mappedBy="order", cascade = CascadeType.ALL)
	private List<OrderItem> orderItems;
	private Date orderDate;
	private float totalOrderPrice;
	private int totalOrderQuantity;
	
	
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	public void addOrderItem(OrderItem orderItem) {
		if(orderItem!=null) {
			if(orderItems==null) {
				orderItems = new ArrayList<>();
			}
			orderItems.add(orderItem);
			orderItem.setOrder(this);
		
		}
	}
	public float getTotalOrderPrice() {
		return totalOrderPrice;
	}
	public void setTotalOrderPrice(float totalOrderPrice) {
		this.totalOrderPrice = totalOrderPrice;
	}
	public int getTotalOrderQuantity() {
		return totalOrderQuantity;
	}
	public void setTotalOrderQuantity(int totalOrderQuantity) {
		this.totalOrderQuantity = totalOrderQuantity;
	}
	
	
	
	
	
}
