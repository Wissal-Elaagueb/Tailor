package com.project.tailor.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.tailor.dao.BrandRepository;
import com.project.tailor.entity.Brand;

@RestController
@RequestMapping("/brands")
public class BrandController {
	
	@Autowired
	private BrandRepository brandRepository;
	
	
	@GetMapping()
	public List<Brand> getAllBrands(){
		return brandRepository.findAll();
	}
	
	@GetMapping("/{BrandId}")
	public Brand getBrandById(@PathVariable int BrandId){
		Brand b = brandRepository.findById(BrandId).get();
		//add exception
		return b;
	}
	

	@PostMapping("")
	public String addBrand() {
		Brand b= new Brand();
		b.setName("iris2");
		brandRepository.save(b);
		return "saved";
	}
	
	@DeleteMapping("/{BrandId}")
	public Brand deleteB(@PathVariable int BrandId) {
		Brand b = brandRepository.findById(BrandId).get();
		
		if (b!=null) {
			brandRepository.deleteById(BrandId);
		}
		    
		//add exception
		return b;
	}
	
	

}
