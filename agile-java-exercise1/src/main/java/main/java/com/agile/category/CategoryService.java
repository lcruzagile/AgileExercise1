package main.java.com.agile.category;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.java.com.agile.exception.NotFoundException;

@Service
public class CategoryService {
	
	@Autowired
	CategoryRepository categoryRepository;
	
	public List<Category> getAllCategories(){
		List<Category> categories = new ArrayList<>();
		categoryRepository.findAll().forEach(categories::add);
		return categories;
	}
	
	public Category getCategory(String name) throws NotFoundException{
		Category category = categoryRepository.findOne(name);
		
		if (category == null) {
			throw new NotFoundException(name);
		}
		return category;
	}
	
	public void addCategory(Category category) {
		categoryRepository.save(category);
	}
	
}
