package com.project.tailor.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.tailor.dao.CategoryRepository;
import com.project.tailor.entity.Brand;
import com.project.tailor.entity.Category;

@RestController
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	private CategoryRepository categoryRepository;
	
	
	@GetMapping()
	public  List<Category> getAllCategories(){
		return categoryRepository.findAll();
	}
	
	
	@GetMapping("/{CategoryId}")
	public  Category getCategoryById(@PathVariable int CategoryId){
		Category c = categoryRepository.findById(CategoryId).get();
		//add exception
		return c;
	}
	
	@PostMapping("")
	public  String addBrand() {
		Category  c= new Category();
		c.setName("silky");
		c.setDescription("all about silky products");
		categoryRepository.save(c);
		return "SAVED";
	}
}
