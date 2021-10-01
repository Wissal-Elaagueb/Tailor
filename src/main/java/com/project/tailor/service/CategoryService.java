package com.project.tailor.service;

import java.util.List;

import com.project.tailor.entity.Category;



public interface CategoryService {

	public List<Category> findAll();
	
	public Category findById(int id);
	
	public void save(Category brand);
	
	public void deleteById(int id);
}
