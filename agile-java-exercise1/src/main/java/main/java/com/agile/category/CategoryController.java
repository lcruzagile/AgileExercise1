package main.java.com.agile.category;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import main.java.com.agile.exception.NotFoundException;
import main.java.com.agile.player.Player;
import main.java.com.agile.player.PlayerService;

@RestController
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	@Autowired
	private CategoryPlayerService categoryPlayerService;
	@Autowired
	private PlayerService playerService;
	
	// Creates logger
	final Logger logger = LogManager.getLogger(CategoryController.class);
	
	public CategoryController() {
		super();
	}
	
	@RequestMapping("/")
	public void initializeTables() {
		if (!categoryService.getAllCategories().isEmpty()){
			return;
		}
		logger.debug("Initialize Database");
		//Initialize Database
		categoryService.addCategory(new Category("Attack"));
		categoryService.addCategory(new Category("Defense"));
		categoryService.addCategory(new Category("Magic"));
		categoryService.addCategory(new Category("Crafting"));
		
	}
	
	@RequestMapping("/category")
	public List<Category> getAllCategories() {
		logger.debug("Initialize Database");
		return categoryService.getAllCategories();
	}
	
//	@RequestMapping(method=RequestMethod.POST, value="/category")
//	public void addPlayer(@RequestBody Category category) {
//		categoryService.addCategory(category);
//	}

	@RequestMapping("/category/player")
	public List<Player> getPlayersOverall() {
		logger.debug("List Players Overall Category");
		return playerService.getPlayerOverall();
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/category/{categoryId}/player/{playerId}")
	public void addCategoryPlayer(@RequestBody CategoryPlayer categoryPlayer, @PathVariable("categoryId") String categoryId,  @PathVariable("playerId") String playerId) {
		logger.debug("Adding Category "+categoryId+" information to Player "+playerId);
		Category category;
		try {
			category = categoryService.getCategory(categoryId);
			categoryPlayer.setCategory(category);
			playerService.addPlayerCategory(playerId, categoryPlayer);
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/category/player/{playerId}")
	public List<CategoryPlayer> getCategoryPlayerByPlayer( @PathVariable("playerId") String playerId) {
		
		try {
			return this.categoryPlayerService.getAllCategoryPlayerByPlayerName(playerId);
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping("/category/{categoryId}/player")
	public List<Player> getPlayerByCategory(@PathVariable("categoryId") String categoryId) {
		return this.playerService.getPlayersByCategory(categoryId);		
	}
	
	@RequestMapping("/player")
	public List<Player> getPlayers(){
		return playerService.getAllPlayer();
	}
	
	@RequestMapping("/player/{id}")
	public Player getPlayer(@PathVariable("id") String id){
		try {
			return playerService.getPlayer(id);
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/player")
	public void addPlayer(@RequestBody Player player) {
		playerService.addPlayer(player);
	}

	@RequestMapping("/player/{player1Id}/compare/{player2Id}")
	public List<Player> getComparePlayers(@PathVariable("player1Id") String player1Id, @PathVariable("player2Id") String player2Id){
		return playerService.getComparePlayers(player1Id, player2Id);
	}
}
