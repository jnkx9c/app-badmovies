package org.kilgore.badmovies;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kilgore.badmovies.entity.Order;
import org.kilgore.badmovies.entity.OrderItem;
import org.kilgore.badmovies.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BadmoviesWebApplicationTests {

	@Autowired
	private OrderRepo orderRepo;
	
	@Test
	public void contextLoads() {
	}
	
	@Test
	@Transactional
	public void testOrder() {
		Optional<Order> optional = orderRepo.findById(5);
		if(optional.isPresent()) {
			System.out.println(optional.get().getOrderDate());	
			Order order = optional.get();
			List<OrderItem> orderItems = order.getOrderItems();
			for(OrderItem oi: orderItems) {
				System.out.println("     "+oi.getId());
			}
		}
		
	}

}
