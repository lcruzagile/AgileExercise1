package main.java.com.agile.category;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Category implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String name;

	public Category() {
	}
	
	public Category(String category) {
		super();
		this.name = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String category) {
		this.name = category;
	}
}
