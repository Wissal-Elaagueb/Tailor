package com.project.tailor.dto;

import java.util.List;
import javax.validation.constraints.*;



import lombok.Data;

@Data
public class ProductRequestDTO {
	
	private Integer id;
	
	@NotBlank(message = "Product name can't be blank")
	private String name;
	
	@Digits(message="Product code must be a number of 8 digits", fraction = 0, integer = 8)
	@Min(value=10000000,message="code must be a number of 8 digits")
	private Integer code;
	
	@Size(max=255, message="The descripption can't be more than 255 careters")
	@NotBlank(message="Product description can't be blank")
	private String description;
	
	@Size(min=64, max=64, message="Photos length can' be more than 64")
	private String photos;
	
	@NotBlank(message="Product color can't be blank")
	private String color;
	
	//table of available size I guess: change it to many to many
	@NotBlank(message="Product size can't be blank")
	private String size;
	
	@NotBlank(message="Product fabric can't be blank")
	private String fabric;

	@NotNull(message="Product can't be without a brand")
	private Integer brandId;
	
	@NotEmpty(message="Product can't be without at least one category")
	private List<Integer> categoriesId;

	

	
	
}
