package com.project.tailor.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.tailor.dao.ProductRepository;
import com.project.tailor.dto.ProductRequestDTO;
import com.project.tailor.entity.Brand;
import com.project.tailor.entity.Category;
import com.project.tailor.entity.Product;
import com.project.tailor.exceptionhandeling.BadRequestException;

import lombok.extern.slf4j.Slf4j;

@Service
public class ProductServiceImpl implements ProductService {

	private static Logger log = LoggerFactory.getLogger(Slf4j.class);
	
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private BrandService brandService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}
	
	
	@Override
	public Product findById(Integer id) throws BadRequestException {
		Optional<Product> product = productRepository.findById(id);

		if (product.isEmpty()) {
			throw new BadRequestException("Product not found - "+id);
		}

		return product.get();
	}

	
	
	@Override
	public void save(ProductRequestDTO product) throws BadRequestException {
		
		Brand brand = brandService.findById(product.getBrandId());		
		
		List<Integer> categoriesId = product.getCategoriesId();
		List<Category> categories= new ArrayList();
		Category category;
		for (Integer i : categoriesId) {
		  	category=  categoryService.findById(i);
		  	categories.add(category);
		}
		
		Product finalProduct = new Product(product,brand,categories);
		
		productRepository.save(finalProduct);
	}

	
	
	@Override
	public void update(Integer id, Product product) throws BadRequestException {
		findById(id);	
		product.setId(id);
		productRepository.save(product);
	}

	
	
	@Override
	public void deleteById(Integer id) throws BadRequestException {
		findById(id);	
		productRepository.deleteById(id);
	}

}
