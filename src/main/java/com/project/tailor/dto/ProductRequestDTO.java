package com.project.tailor.dto;

import java.util.List;

import javax.validation.constraints.*;



import lombok.Data;

@Data
public class ProductRequestDTO {
	
	private Integer id;
	
	@NotBlank(message = "Product name can't be null")
	private String name;
	
	@Digits(message="code must be a number of 8 digits", fraction = 0, integer = 8)
	@Min(value=10000000,message="code must be a number of 8 digits")
	private Integer code;
	
	@Size(max=255, message="The descripption can't be more than 255 careters")
	@NotBlank(message="description can't be blank")
	private String description;
	
	private String photos;
	
	private String color;
	
	private String size;
	
	private String fabric;

	private Integer brandId;
	
	private List<Integer> categoriesId;

	

	
	
}
