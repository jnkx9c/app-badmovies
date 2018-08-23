package org.kilgore.badmovies.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Rating")
public class Rating extends BaseEntityObject{

	
	private String source;
	private String value;
	
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
