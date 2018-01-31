package main.java.com.agile.player;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import main.java.com.agile.category.*;

@Entity
public class Player  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private String name;
	
	@OneToMany( cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	List<CategoryPlayer> categoryPlayer;
	
	public Player() {
	}
	
	public List<CategoryPlayer> getCategoryPlayer() {
		return categoryPlayer;
	}

	public void setCategoryPlayer(List<CategoryPlayer> categoryPlayer) {
		this.categoryPlayer = categoryPlayer;
	}

	public Player(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
