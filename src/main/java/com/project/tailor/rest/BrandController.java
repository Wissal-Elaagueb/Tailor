package com.project.tailor.rest;

import java.util.List;


import com.project.tailor.exceptionhandeling.BadRequestException;
import com.project.tailor.exceptionhandeling.SuccessResponse;

import lombok.extern.slf4j.Slf4j;
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

import com.project.tailor.entity.Brand;
import com.project.tailor.service.BrandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;

@RestController
@RequestMapping("/brands")

public class BrandController {
	
	private static Logger log = LoggerFactory.getLogger(Slf4j.class);
	
	@Autowired
	private BrandService brandService;


	@GetMapping()
	public ResponseEntity<SuccessResponse> findAllBrands() {

		log.info("calling method : findAllBrands()");
		
		List<Brand> brands= brandService.findAll();

		SuccessResponse response= new SuccessResponse(brands,System.currentTimeMillis());
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	
	
	@GetMapping("/{brandId}")
	public ResponseEntity<SuccessResponse> findBrandById(@PathVariable Integer brandId) throws Exception {
		
		log.info("calling method : findBrandById()");
		
		Brand brand = brandService.findById(brandId);

		SuccessResponse response= new SuccessResponse(brand,System.currentTimeMillis());
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	
	
	@PostMapping("")
	public ResponseEntity<SuccessResponse> createBrand(@Valid @RequestBody Brand brand)  {
		
		log.info("calling method : createBrand()");
		
		brandService.save(brand);
		
		SuccessResponse response= new SuccessResponse(brand,System.currentTimeMillis());
		return new ResponseEntity<>(response, HttpStatus.OK);
		}
	
	
	
	
	@PutMapping("/{brandId}")
	public ResponseEntity<SuccessResponse> updateBrand(
				@PathVariable Integer brandId, 
				@Valid @RequestBody Brand brand) throws BadRequestException{

		log.info("calling method : updateBrand()");
		
		brandService.update(brandId, brand);

		SuccessResponse response= new SuccessResponse(brand,System.currentTimeMillis());
		return new ResponseEntity<>(response, HttpStatus.OK);
		
	}
	
	
	@DeleteMapping("/{brandId}")
	public ResponseEntity<SuccessResponse> deleteBrand(@Valid @PathVariable Integer brandId) throws BadRequestException{

		log.info("calling method : deleteBrand()");
		
		Brand brand = brandService.deleteById(brandId);	
		
		
		
		SuccessResponse response= new SuccessResponse(brand,System.currentTimeMillis());
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	
}
