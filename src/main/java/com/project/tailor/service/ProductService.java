package com.project.tailor.service;

import java.util.List;


import com.project.tailor.dto.ProductRequestDTO;
import com.project.tailor.entity.Product;
import com.project.tailor.exceptionhandeling.BadRequestException;

public interface ProductService {

	public List<Product> findAll();
	
	public Product findById(Integer id) throws BadRequestException;
	
	void update(Integer id, ProductRequestDTO product) throws BadRequestException, InterruptedException;
	
	public void deleteById(Integer id) throws BadRequestException;

	void save(ProductRequestDTO product) throws BadRequestException, InterruptedException;

	void save(ProductRequestDTO product, Integer id) throws BadRequestException, InterruptedException;

}
