package main.java.com.agile.player;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.java.com.agile.category.Category;
import main.java.com.agile.category.CategoryPlayer;
import main.java.com.agile.exception.NotFoundException;

@Service
public class PlayerService {
	@Autowired
	PlayerRepository playerRepository;
	
	public PlayerService() {
		super();
	}

	public List<Player> getAllPlayer(){
		List<Player> players = new ArrayList<>();
		playerRepository.findAll().forEach(players::add);
		return players;
	}
	
	public Player getPlayer(String name) throws NotFoundException {
		Player player = playerRepository.findByName(name);
		if(player == null) {
			throw new NotFoundException(name);
		}
		return player;
	}
	
	public void addPlayer(Player player) {
		playerRepository.save(player);
	}
	
	public void addPlayerCategory(String playerId, CategoryPlayer categoryPlayer) {
		Player player = this.playerRepository.findByName(playerId);
		
		categoryPlayer.setPlayer(player);
		player.getCategoryPlayer().add(categoryPlayer);
		
		this.playerRepository.save(player);
	}
	
	public List<Player> getPlayersByCategory(String categoryName) {
		
		List<Player> players = this.playerRepository.findByCategoryPlayerCategoryName(categoryName) ;
		players.forEach( c -> c.getCategoryPlayer().removeIf( cp -> !cp.getCategory().getName().equals(categoryName) ) );
		return players;
	}
	
	public List<Player> getPlayerOverall(){
		List<Player> players = (List<Player>) this.playerRepository.findAll();
		
		getOverallCategory(players, true);

		return (List<Player>) players;
	}
	
	public List<Player> getComparePlayers(String player1Name, String player2Name ){
		List<Player> players = new ArrayList<Player>();
		
		players.add(this.playerRepository.findByName(player1Name));
		players.add(this.playerRepository.findByName(player2Name));
				
		getOverallCategory(players, false);

		return players;
	}
	
	private List<Player> getOverallCategory(List<Player> playersRaw, boolean onlyOverall ){
		List<Player> players = new ArrayList<Player>();
		
		for(Player player : playersRaw) {
			players.add(player);
			CategoryPlayer categoryPlayerOverall = new CategoryPlayer( new Category("Overall"), player,
					player.getCategoryPlayer().stream().mapToInt( i -> i.getExpPoints() ).sum(), 
					player.getCategoryPlayer().stream().mapToInt( i -> i.getCategoryLevel() ).sum() );
			if (onlyOverall)
				player.getCategoryPlayer().clear();
			player.getCategoryPlayer().add(categoryPlayerOverall);
		}
		return players;
	}
	
}
