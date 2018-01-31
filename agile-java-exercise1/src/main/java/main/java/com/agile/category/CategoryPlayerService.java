package main.java.com.agile.category;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.java.com.agile.exception.NotFoundException;

@Service
public class CategoryPlayerService {

	@Autowired
	private CategoryPlayerRepository categoryPlayerRepository;
	
	
//	public List<CategoryPlayer> getAllCategoryPlayersByCategoryName(String categoryName){
//		List<CategoryPlayer> categoryPlayer = new ArrayList<>();
//		
//		categoryPlayerRepository.findByCategoryName(categoryName).forEach(categoryPlayer::add);
//		return categoryPlayer;
//	}
	
	public List<CategoryPlayer> getAllCategoryPlayer(){
		List<CategoryPlayer> categoryPlayer = new ArrayList<>();
		
		categoryPlayerRepository.findAll().forEach(categoryPlayer::add);
		return categoryPlayer;
	}
	
	public void addCategoryPlayer(CategoryPlayer categoryPlayer) {
		
		categoryPlayerRepository.save(categoryPlayer);
		
	}

	public List<CategoryPlayer> getAllCategoryPlayerByCategory(String categoryId) {
		return this.categoryPlayerRepository.findByCategoryName(categoryId);
	}

	public List<CategoryPlayer> getAllCategoryPlayerByPlayerName(String player) throws NotFoundException{
		List<CategoryPlayer> catPlayer = this.categoryPlayerRepository.findByPlayerName(player);
		if (catPlayer.isEmpty()) {
			throw new NotFoundException(player);
		}
		return catPlayer;
	}
	
}
