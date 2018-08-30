package org.kilgore.badmovies.repo;

import java.util.List;

import org.kilgore.badmovies.entity.Order;
import org.kilgore.badmovies.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepo extends CrudRepository<Order, Integer>{

	
	List<Order> findByUser(User user);
	
	//adding the 'user' criteria ensures that users can just look at any order that isn't theirs
	Order findByIdAndUser(Integer orderId, User user);
}
