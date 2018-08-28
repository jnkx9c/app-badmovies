package org.kilgore.badmovies.repo;

import java.util.List;

import org.kilgore.badmovies.entity.Order;
import org.kilgore.badmovies.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepo extends CrudRepository<Order, Integer>{

	
	public List<Order> findOrdersByUser(User user);
}
