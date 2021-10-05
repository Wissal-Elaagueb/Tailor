package com.project.tailor.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.tailor.dao.CategoryRepository;
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
	public void save(Category category) {
		categoryRepository.save(category);
	}
	
	@Override
	public void update(Integer id,Category category) throws BadRequestException {
		findById(id);
		categoryRepository.save(category);

	}

	@Override
	public void deleteById(int id) throws BadRequestException {
		findById(id);
		categoryRepository.deleteById(id);
	}

}
