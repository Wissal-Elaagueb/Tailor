package com.project.tailor.service;

import java.io.FileNotFoundException;
import java.util.List;

import com.project.tailor.dto.FilterProductDTO;
import com.project.tailor.dto.ProductRequestDTO;
import com.project.tailor.entity.Product;
import com.project.tailor.exceptionhandeling.BadRequestException;

public interface ProductService {

	public List<Product> findAll();
	
	public Product findById(Integer id) throws BadRequestException;
	
	void update(Integer id, ProductRequestDTO product) throws BadRequestException, InterruptedException;
	
	public Product deleteById(Integer id) throws BadRequestException;

	void save(ProductRequestDTO product) throws BadRequestException, InterruptedException;

	void save(ProductRequestDTO product, Integer id) throws BadRequestException, InterruptedException;

	List<Product> filter(String name,String color,String size,String fabric,Integer brandId,List<Integer> categoriesId,Integer pageNumber,Integer pageSize);


}
