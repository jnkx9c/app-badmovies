package org.kilgore.badmovies.repo;

import org.kilgore.badmovies.entity.Authority;
import org.springframework.data.repository.CrudRepository;

public interface AuthorityRepo extends CrudRepository<Authority, Integer>{

	Authority findByAuthority(String authority);
}
