package test.java.com.agile;

import static org.junit.Assert.*;

import org.junit.Test;

import main.java.com.agile.category.CategoryController;
import main.java.com.agile.exception.NotFoundException;
import main.java.com.agile.player.Player;
import main.java.com.agile.player.PlayerService;

public class PlayerTest {

	@Test
	public void getPlayerTest() {
//		PlayerService playerService = new PlayerService();
//		
//		playerService.addPlayer( new Player("testPlayer"));
//		
//		try {
//			assertEquals( playerService.getPlayer("testPlayer").getName(), "testPlayer");
//		} catch (NotFoundException e) {
//			e.printStackTrace();
//		}
		
		
		
		CategoryController catController = new CategoryController();
		
		catController.addPlayer(new Player("testPlayer"));
		
		assertEquals( catController.getPlayer("testPlayer").getName(), "testPlayer");
		
	}

}
