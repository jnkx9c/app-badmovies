package org.kilgore.badmovies.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name="Authorities")
public class Authority extends BaseEntityObject implements GrantedAuthority{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(unique=true)
	private String authority;
	
	@Override
	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

}
