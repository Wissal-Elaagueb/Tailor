package com.project.tailor.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.tailor.dao.CategoryRepository;
import com.project.tailor.entity.Brand;
import com.project.tailor.entity.Category;
import com.project.tailor.exceptionhandeling.ResourceNotFoundException;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	
	
	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}
	

	@Override
	public Category findById(int id) {
		Optional<Category> result = categoryRepository.findById(id);
		
        Category category = null;
        
        if (result.isPresent()) 
       	 category = result.get();
        else 
        	throw new ResourceNotFoundException("Category Id not Found - "+id);
        
        return category;
	}

	@Override
	public void save(Category category) {
		categoryRepository.save(category);

	}

	@Override
	public void deleteById(int id) {
		categoryRepository.deleteById(id);
	}

}
