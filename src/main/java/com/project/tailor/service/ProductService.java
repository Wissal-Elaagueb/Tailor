package com.project.tailor.service;

import java.util.List;

import com.project.tailor.entity.Product;
import com.project.tailor.exceptionhandeling.BadRequestException;

public interface ProductService {

public List<Product> findAll();
	
	public Product findById(Integer id) throws BadRequestException;
	
	public void save(Product product);
	
	void update(Integer id, Product product) throws BadRequestException;
	
	public void deleteById(Integer id) throws BadRequestException;
}
