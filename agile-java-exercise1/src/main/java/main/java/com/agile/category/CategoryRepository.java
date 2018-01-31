package main.java.com.agile.category;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, String> {
	
	public List<Category> findByName(String category);

}

