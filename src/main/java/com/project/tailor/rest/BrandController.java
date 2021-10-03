package com.project.tailor.rest;

import java.util.List;


import com.project.tailor.exceptionhandeling.BadRequestException;
import lombok.extern.slf4j.Slf4j;
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

import com.project.tailor.entity.Brand;
import com.project.tailor.exceptionhandeling.ResourceNotFoundException;
import com.project.tailor.service.BrandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;

@RestController
@RequestMapping("/brands")
@Slf4j
public class BrandController {
	
	@Autowired
	private BrandService brandService;


	@GetMapping()
	public List<Brand> getAllBrands() throws Exception {
		try {
			log.debug("*****************************************--Application Started--");
			return brandService.findAll();
		}catch(Exception e) {
			 throw new Exception(e.getMessage());
		}
	}
	
	
	
	@GetMapping("/{brandId}")
	public ResponseEntity<Brand> findById(@PathVariable Integer brandId) throws BadRequestException {
		//Logs
		Brand brand = brandService.findById(brandId);
		return ResponseEntity.ok(brand);
	}
	
	
	
	@PostMapping("")
	public ResponseEntity<String> addBrand(@Valid @RequestBody Brand brand) throws Exception {
			try {
				//validating data
				brandService.save(brand);
				
				return ResponseEntity.ok()
				        .body("Brand saved with succes");
				
			} catch (Exception e) {
				throw new Exception(e.getMessage());
			}
	}
	
	
	
	
	@PutMapping("")
	public ResponseEntity<String> updateBrand(@RequestBody Brand brand) throws Exception {
			try {
				if (brand.getId()==null)
						throw  new RuntimeException("Id is missing"); //teb3a data validation

				brandService.findById(brand.getId());	
				brandService.save(brand);

				return ResponseEntity.ok()
				        .body("Brand updatetd with succes");
				
			} catch (ResourceNotFoundException e) {
				throw new ResourceNotFoundException(e.getMessage());
			}catch (Exception e) {
				throw new Exception(e.getMessage());
			}
	}
	
	
	@DeleteMapping("/{brandId}")
	public ResponseEntity<String> deleteBrand(@PathVariable int brandId) throws Exception {
		try {
				brandService.findById(brandId);
				brandService.deleteById(brandId);	
				
				return ResponseEntity.ok()
				        .body("Brand deleted with succes");
				
		} catch (ResourceNotFoundException e) {
				throw new ResourceNotFoundException(e.getMessage());
		}catch (Exception e) {
				throw new Exception(e.getMessage());
		}
	}

	
}
