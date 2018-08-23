package org.kilgore.badmovies.repo;

import org.kilgore.badmovies.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Integer> {

	User findByUsername(String username);

}
