package main.java.com.agile.category;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CategoryPlayerRepository extends CrudRepository<CategoryPlayer, String> {
	
	public List<CategoryPlayer> findByCategoryName(String categoryName);
	public List<CategoryPlayer> findByPlayerName(String name);
}
