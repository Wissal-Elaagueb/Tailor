package com.project.tailor.rest;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.tailor.entity.Brand;
import com.project.tailor.service.BrandService;

@RestController
@RequestMapping("/brands")
public class BrandController {
	
	@Autowired
	private BrandService brandService;
	
	
	/*
	@GetMapping()
	public List<Brand> getAllBrands() {
		try {
			return brandService.findAll();
		}catch(Exception e) {
			 throw new Exception("Internal server exception while getting all Brands");
		}
	}*/
	
	
	@GetMapping()
	public List<Brand> getAllBrands() {
			return brandService.findAll();
	}
	
	
	
	@GetMapping("/{brandId}")
	public Brand getBrandById(@PathVariable int brandId) throws Exception{
			//add validation for id format
			Brand brand = brandService.findById(brandId);

			return brand;
	}
	
	
	
	@PostMapping("")
	public Brand addBrand(@RequestBody Brand brand) throws Exception {
			//validating data
			
			brandService.save(brand);
			
			return brand;
	}
	
	
	
	
	@PutMapping("")
	public Brand updateBrand(@RequestBody Brand brand) throws Exception {
			if (brand.getId()==null)
					throw  new RuntimeException("Id is missing");

			Brand tempBrand = brandService.findById(brand.getId());	
			
			brandService.save(brand);
			
			return brand;
	}
	
	
	@DeleteMapping("/{brandId}")
	public Brand deleteBrand(@PathVariable int brandId) throws Exception {
			Brand brand = brandService.findById(brandId);

			brandService.deleteById(brandId);
			
			return brand;
	}
	
}
