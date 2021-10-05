package com.project.tailor.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.project.tailor.entity.Category;
import com.project.tailor.exceptionhandeling.BadRequestException;
import com.project.tailor.exceptionhandeling.SuccessResponse;
import com.project.tailor.service.CategoryService;

import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/categories")
public class CategoryController {

	private static Logger log = LoggerFactory.getLogger(Slf4j.class);
	
	@Autowired
	private CategoryService categoryService;
	
	
	@GetMapping()
	public  ResponseEntity<SuccessResponse> findAllCategories() throws Exception{
	
		log.info("calling method : findAllCategories()");
		
		List<Category> categories = categoryService.findAll();
		
		SuccessResponse response= new SuccessResponse(categories,System.currentTimeMillis());
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}
	
	
	
	@GetMapping("/{categoryId}")
	public  ResponseEntity<SuccessResponse> findCategoryById(@PathVariable Integer categoryId) throws BadRequestException {

		log.info("calling method : findCategoryById()");
		
		Category category = categoryService.findById(categoryId);
		
		SuccessResponse response= new SuccessResponse(category,System.currentTimeMillis());
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}
	
	
	
	@PostMapping("")
	public ResponseEntity<SuccessResponse> createCategory(@Valid @RequestBody Category category) throws Exception {
				
		log.info("calling method : createCategory()");
		
		categoryService.save(category);
		
		SuccessResponse response= new SuccessResponse("Category created with success",System.currentTimeMillis());
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}
	
	
	
	@PutMapping("/{categoryId}")
	public ResponseEntity<SuccessResponse> updateCategory(
				@Valid @PathVariable Integer categoryId,
				@Valid @RequestBody Category category) throws BadRequestException  {
				
		log.info("calling method : updateCategory()");
		
		categoryService.update(categoryId,category);
		
		SuccessResponse response= new SuccessResponse("Category updated with success",System.currentTimeMillis());
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
				
	}
	
	
	
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<SuccessResponse> deleteCategory(@PathVariable int categoryId) throws BadRequestException  {
			
		log.info("calling method : deleteCategory()");
		
		categoryService.deleteById(categoryId);
		
		SuccessResponse response= new SuccessResponse("Category deleted with success",System.currentTimeMillis());
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
			
	}
	
}
