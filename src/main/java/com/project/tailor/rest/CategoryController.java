package com.project.tailor.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.project.tailor.exceptionhandeling.ResourceNotFoundException;
import com.project.tailor.service.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	
	@GetMapping()
	public  List<Category> getAllCategories() throws Exception{
			try {
				return categoryService.findAll();
			}catch(Exception e) {
				 throw new Exception(e.getMessage());
			}
	}
	
	
	
	@GetMapping("/{categoryId}")
	public  Category getCategoryById(@PathVariable int categoryId) throws Exception{
			try {
				Category category = categoryService.findById(categoryId);
				return category;
				
			} catch (ResourceNotFoundException e) {
				throw new ResourceNotFoundException(e.getMessage());
		}catch (Exception e) {
				throw new Exception(e.getMessage());
		}
	}
	
	
	
	@PostMapping("")
	public ResponseEntity<String> addBrand(@RequestBody Category category) throws Exception {
		try {
				//validating data
				categoryService.save(category);
				
				return ResponseEntity.ok()
				        .body("Category saved with succes");
				
			} catch (Exception e) {
				throw new Exception(e.getMessage());
			}
	}
	
	
	
	@PutMapping("")
	public ResponseEntity<String> updateCategory(@RequestBody Category category) throws Exception {
			try {
				if (category.getId()==null) 
					throw  new RuntimeException("Id is missing");

				categoryService.findById(category.getId());
				categoryService.save(category);
				
				return ResponseEntity.ok()
				        .body("Category updatetd with succes");
				
			} catch (ResourceNotFoundException e) {
				throw new ResourceNotFoundException(e.getMessage());
			}catch (Exception e) {
				throw new Exception(e.getMessage());
			}
	}
	
	
	
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<String> deleteCategory(@PathVariable int categoryId) throws Exception {
		try {
			
			categoryService.findById(categoryId);
			categoryService.deleteById(categoryId);
				
			return ResponseEntity.ok()
			        .body("Category deleted with succes");
			
		} catch (ResourceNotFoundException e) {
				throw new ResourceNotFoundException(e.getMessage());
		}catch (Exception e) {
				throw new Exception(e.getMessage());
		}
	}
	
}
