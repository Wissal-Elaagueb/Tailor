package com.project.tailor.service;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.stereotype.Service;

import com.project.tailor.dao.ProductRepository;
import com.project.tailor.dto.FilterProductDTO;
import com.project.tailor.dto.ProductRequestDTO;
import com.project.tailor.entity.Brand;
import com.project.tailor.entity.Category;
import com.project.tailor.entity.File;
import com.project.tailor.entity.Product;
import com.project.tailor.exceptionhandeling.BadRequestException;

//import static jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyle.title;


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

	@Autowired
	EntityManager entityManager;
	
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
			List<Integer> newImages = product.getImages(); 
			for (File f: oldImages) {
				if(!newImages.contains(f.getId())) {
					fileService.delete(f.getId());
				    
				}
					
			}	
		}
		Product finalProduct = new Product(product,brand,categories);
		finalProduct.setId(id);
		productRepository.save(finalProduct);
		

		List<Integer> imagesId = product.getImages();
		File image;
		for (Integer i : imagesId) {
		  	image=  fileService.findFileById(i);
		  	if (image.getProduct()!=null && image.getProduct().getId()!=finalProduct.getId()) {
		  			throw new BadRequestException("the selected image id - "+ i +" is already associated with another product");
		  	}
		  	  
		  	image.setProduct(finalProduct);
		  	fileService.update(i,image);
		}
	}
	

	public void save(ProductRequestDTO product) throws BadRequestException, InterruptedException{
		save(product,null);
	}
	
	
	@Override
	public void update(Integer id, ProductRequestDTO product) throws BadRequestException, InterruptedException {
		findById(id);	
		save(product,id);
	}

	
	
	@Override
	public Product deleteById(Integer id) throws BadRequestException {
		Product product = findById(id);	
		productRepository.deleteById(id);
		try {
			List<File> files = product.getImages();
			
			if (files.size() > 0) {
				for (File file : files) {
					java.io.File convFile = new java.io.File ("C:\\Users\\user16\\Desktop\\All\\tech\\Spring\\Tailor\\"+file.getPath());
					convFile.delete();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	}
	
	@Override
	public List<Product> filter(String name,String color,String size,String fabric,Integer brandId,List<Integer> categoriesId,Integer pageNumber,Integer pageSize) throws BadRequestException{


		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Product> cq = cb.createQuery(Product.class);
		Root<Product> product = cq.from(Product.class);

		List<Predicate> predicates = new ArrayList<>();
		
		if (!name.equals("%"))
			 predicates.add(cb.equal(product.get("name"),name));
		if (!color.equals("%"))
			predicates.add(cb.equal(product.get("color"),color));
		if (!size.equals("%"))
			predicates.add(cb.equal(product.get("size"),size));
		if (!fabric.equals("%"))
			predicates.add(cb.equal(product.get("fabric"),fabric));
		if (brandId != null) {
			Brand b = brandService.findById(brandId);
			 predicates.add(cb.equal(product.get("brand"),b));
		}
		
		if (categoriesId != null) {
			Set<Category> categories= new HashSet<Category>();
			for (Integer id :categoriesId) {
				categories.add(categoryService.findById(id));
			}
			predicates.add(cb.equal(product.get("categories"),categories));
		}
		System.out.println(categoriesId);
		
		Pageable page= PageRequest.of(pageNumber, pageSize);
		cq.where(predicates.toArray(new Predicate[0]));
		TypedQuery<Product> query = entityManager.createQuery(cq);
		List<Product> all = query.getResultList();
		/*
		
		List<Product> all;

		if (name.strip().equals(""))
			name="%";
		else
			name=name+"%";
		if (color.strip().equals(""))
			color="%";
		if (size.strip().equals(""))
			size="%";
		if (fabric.strip().equals(""))
			fabric="%";
		Pageable page= PageRequest.of(pageNumber, pageSize);
		if (brandId==null)
			all= productRepository.filter(name,color,size,fabric,page);
		else
			all= productRepository.filter(name,color,size,fabric,brandId,page);
		*/

		return all;
	}


}
