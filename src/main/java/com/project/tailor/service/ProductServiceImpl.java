package com.project.tailor.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.tailor.dao.ProductRepository;
import com.project.tailor.dto.ProductRequestDTO;
import com.project.tailor.entity.Brand;
import com.project.tailor.entity.Category;
import com.project.tailor.entity.File;
import com.project.tailor.entity.Product;
import com.project.tailor.exceptionhandeling.BadRequestException;


@Service
public class ProductServiceImpl implements ProductService {

	
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private BrandService brandService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private FileService fileService;
	
	
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
	@Transactional(rollbackOn = BadRequestException.class)
	public void save(ProductRequestDTO product, Integer id) throws BadRequestException, InterruptedException {
		
		Brand brand = brandService.findById(product.getBrandId());		
		
		List<Integer> categoriesId = product.getCategoriesId();
		List<Category> categories= new ArrayList<>();
		Category category;
		for (Integer i : categoriesId) {
		  	category=  categoryService.findById(i);
		  	categories.add(category);
		}
		
		//check if in old verion there images that are not in the new one and delete them
		if (id!=null) {
			List<File> oldImages = findById(id).getImages();
			List<Integer> imagesId = product.getImages();
			for (File f: oldImages) {
				if(!imagesId.contains(f.getId()))
					f.setProduct(null);
			}	
		}
		
		Product finalProduct = new Product(product,brand,categories);
		finalProduct.setId(id);
		productRepository.save(finalProduct);
		

		List<Integer> imagesId = product.getImages();
		File image;
		for (Integer i : imagesId) {
		  	image=  fileService.findFileById(i);
		  	if (image.getProduct()!=null && image.getProduct()==finalProduct) {
		  			throw new BadRequestException("the selected image id - "+ i +" is already associated with another product");
		  	}
		  	  
		  	image.setProduct(finalProduct);
		  	System.out.println(image.getProduct().getDescription());
		  	fileService.update(i,image);
		}
	}
	
	@Override
	@Transactional(rollbackOn = BadRequestException.class)
	public void save(ProductRequestDTO product) throws BadRequestException, InterruptedException{
		save(product,null);
	}
	
	
	@Override
	public void update(Integer id, ProductRequestDTO product) throws BadRequestException, InterruptedException {
		findById(id);	
		save(product,id);
	}

	
	
	@Override
	public void deleteById(Integer id) throws BadRequestException {
		findById(id);	
		productRepository.deleteById(id);
	}

}
