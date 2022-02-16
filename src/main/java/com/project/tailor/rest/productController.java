package com.project.tailor.rest;

import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.tailor.dto.ProductRequestDTO;
//import com.project.tailor.dto.ProductResponseDTO;
import com.project.tailor.entity.Product;
import com.project.tailor.exceptionhandeling.BadRequestException;
import com.project.tailor.exceptionhandeling.SuccessResponse;
import com.project.tailor.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/products")

public class productController {

	private static Logger log = LoggerFactory.getLogger(Slf4j.class);
	
	@Autowired
	private ProductService productService;


	@GetMapping()
	public ResponseEntity<SuccessResponse> findAllProducts() {

		log.info("calling method : findAllProductss()");
		
		List<Product> products= productService.findAll();

		SuccessResponse response= new SuccessResponse(products,System.currentTimeMillis());
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	
	@GetMapping("/{productId}")
	public ResponseEntity<SuccessResponse> findProductById(@PathVariable Integer productId) throws Exception {
		
		log.info("calling method : findProductById()");
		
		Product product = productService.findById(productId);
		//ProductResponseDTO prd= new ProductResponseDTO(product);
		//System.out.println(prd);

		SuccessResponse response= new SuccessResponse(product,System.currentTimeMillis());
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	
	
	@PostMapping("")
	public ResponseEntity<SuccessResponse> createProduct(@Valid @RequestBody ProductRequestDTO product) throws BadRequestException, InterruptedException  {
		
		log.info("calling method : createProduct()");
		
		productService.save(product);
		
		SuccessResponse response= new SuccessResponse(product,System.currentTimeMillis());
		return new ResponseEntity<>(response, HttpStatus.OK);
		}
	
	
	@PutMapping("/{productId}")
	public ResponseEntity<SuccessResponse> updateProduct(
				@PathVariable Integer productId, 
				@Valid @RequestBody ProductRequestDTO product) throws BadRequestException, InterruptedException{

		log.info("calling method : updateProduct()");
		
		productService.update(productId, product);

		SuccessResponse response= new SuccessResponse(product,System.currentTimeMillis());
		return new ResponseEntity<>(response, HttpStatus.OK);
		
	}
	
	
	@DeleteMapping("/{productId}")
	public ResponseEntity<SuccessResponse> deleteProduct(@Valid @PathVariable Integer productId) throws BadRequestException{

		log.info("calling method : deleteProduct()");
		
		Product product = productService.deleteById(productId);	

		SuccessResponse response= new SuccessResponse(product,System.currentTimeMillis());
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	@GetMapping("/try")
	public ResponseEntity<SuccessResponse> filter(@RequestParam(required = false, defaultValue="%") String name,
												@RequestParam(required = false, defaultValue="%") String color,
												@RequestParam(required = false, defaultValue="%") String size,
												@RequestParam(required = false, defaultValue="%") String fabric,
												@RequestParam(required = false) Integer brandId,
												@RequestParam(required = false, defaultValue="0") Integer pageNumber,
												@RequestParam(required = false, defaultValue="30") Integer pageSize,
												@RequestParam(required = false) List<Integer> categoriesId) throws BadRequestException {
		
		log.info("calling method : filter()");
		
		List<Product> products= productService.filter(name,color,size,fabric,brandId,categoriesId,pageNumber,pageSize);

		SuccessResponse response= new SuccessResponse(products,System.currentTimeMillis());
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
}
