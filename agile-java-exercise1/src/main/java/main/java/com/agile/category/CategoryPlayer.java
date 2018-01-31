package main.java.com.agile.category;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import main.java.com.agile.player.Player;

@Entity
public class CategoryPlayer implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@ManyToOne
	private Category category;
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private Player player;
	private int expPoints;
	private int categoryLevel;
	
	public CategoryPlayer() {
	}

	public CategoryPlayer(Category category, Player player, int points, int categoryLevel) {
		super();
		this.category = category;
		this.player = player;
		this.expPoints = points;
		this.categoryLevel = categoryLevel;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	public int getExpPoints() {
		return expPoints;
	}

	public void setExpPoints(int points) {
		this.expPoints = points;
	}

	public int getCategoryLevel() {
		return categoryLevel;
	}

	public void setCategoryLevel(int categoryLevel) {
		this.categoryLevel = categoryLevel;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
}
