package com.project.tailor.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.tailor.dao.BrandRepository;
import com.project.tailor.dao.ProductRepository;
import com.project.tailor.entity.Brand;
import com.project.tailor.entity.Product;
import com.project.tailor.exceptionhandeling.BadRequestException;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public Product findById(Integer id) throws BadRequestException {
		Optional<Product> product = productRepository.findById(id);

		if (product.isEmpty())
			throw new BadRequestException("Product not found - "+id);

		return product.get();
	}

	@Override
	public void save(Product product) {
		productRepository.save(product);
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
