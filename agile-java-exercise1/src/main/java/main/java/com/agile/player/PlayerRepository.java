package main.java.com.agile.player;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Player, String> {
	
	public Player findByName(String name);
	public List<Player> findByCategoryPlayerCategoryName(String Name);
	
}
