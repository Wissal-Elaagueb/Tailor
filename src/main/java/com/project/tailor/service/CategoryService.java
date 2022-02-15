package com.project.tailor.service;

import java.util.List;

import javax.validation.Valid;

import com.project.tailor.dto.CategoryRequestDTO;
import com.project.tailor.entity.Category;
import com.project.tailor.exceptionhandeling.BadRequestException;



public interface CategoryService {

	public List<Category> findAll();
	
	public Category findById(int id) throws BadRequestException;
	
	public void save(@Valid CategoryRequestDTO category);
	
	void update(Integer id, @Valid CategoryRequestDTO category) throws BadRequestException;
	
	public Category deleteById(int id) throws BadRequestException;

	
}
