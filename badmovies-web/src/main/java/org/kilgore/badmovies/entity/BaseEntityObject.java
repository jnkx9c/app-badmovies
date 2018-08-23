package org.kilgore.badmovies.entity;



import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntityObject{

	@Id
	@GeneratedValue
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object obj) {
		if(id!=null && obj!=null && obj instanceof BaseEntityObject) {
			BaseEntityObject beo = (BaseEntityObject) obj;
			if(beo.getId()!=null) {
				return id.equals(beo.getId());
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		if(id!=null) {
			return id.hashCode();
		}else {
			return super.hashCode();	
		}
		
	}
	
	
	
	
	
	
	
	
}
