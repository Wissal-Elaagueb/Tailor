package com.project.tailor.service;

import java.util.List;

import com.project.tailor.entity.Category;
import com.project.tailor.exceptionhandeling.BadRequestException;



public interface CategoryService {

	public List<Category> findAll();
	
	public Category findById(int id) throws BadRequestException;
	
	public void save(Category brand);
	
	void update(Integer id, Category category) throws BadRequestException;
	
	public void deleteById(int id) throws BadRequestException;

	
}
