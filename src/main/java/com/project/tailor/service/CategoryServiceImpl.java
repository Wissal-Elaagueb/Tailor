package com.project.tailor.service;

import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.tailor.dao.CategoryRepository;
import com.project.tailor.dto.CategoryRequestDTO;
import com.project.tailor.entity.Brand;
import com.project.tailor.entity.Category;
import com.project.tailor.exceptionhandeling.BadRequestException;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	
	
	@Override
	public List<Category> findAll() {
	
			return categoryRepository.findAll();
	}
	

	@Override
	public Category findById(int id) throws BadRequestException {
		Optional<Category> category = categoryRepository.findById(id);
     
		if (category.isEmpty()) 
			throw new BadRequestException("Category not found - "+id);
       
		return category.get();
	}

	@Override
	public void save(CategoryRequestDTO category) {
		Category finalCategory = new Category(category);
		categoryRepository.save(finalCategory);
	}
	
	@Override
	public void update(Integer id,CategoryRequestDTO category) throws BadRequestException {
		findById(id);
		Category finalCategory = new Category(category);
		finalCategory.setId(id);
		categoryRepository.save(finalCategory);

	}

	
	@Override
	public Category deleteById(int id) throws BadRequestException {
		Category category= findById(id);
		categoryRepository.deleteById(id);
		return category;
	}

}
