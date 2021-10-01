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
	
	
	@GetMapping()
	public List<Brand> getAllBrands(){
		return brandService.findAll();
	}
	
	
	
	@GetMapping("/{brandId}")
	public Brand getBrandById(@PathVariable int brandId){
		
		Brand brand = brandService.findById(brandId);
		
		if (brand==null) 
			throw  new RuntimeException("Brand id not found -"+brandId);
		
		return brand;
	}
	
	
	
	@PostMapping("")
	public Brand addBrand(@RequestBody Brand brand) {
		
		//validating data
		
		brandService.save(brand);
		
		return brand;
	}
	
	
	
	
	@PutMapping("")
	public Brand updateBrand(@RequestBody Brand brand) {

		if (brand.getId()==null)
				throw  new RuntimeException("Id is missing");
	
		Brand tempBrand = brandService.findById(brand.getId());
		if (tempBrand==null) 
			throw  new RuntimeException("Brand id not found -"+brand.getId());
		
		
		brandService.save(brand);
		
		return brand;
	}
	
	
	@DeleteMapping("/{brandId}")
	public Brand deleteBrand(@PathVariable int brandId) {
		
		Brand brand = brandService.findById(brandId);

		if (brand==null) {
			throw  new RuntimeException("Brand id not found -"+brandId);
		}

		brandService.deleteById(brandId);
		
		return brand;
	}
	
	

}
