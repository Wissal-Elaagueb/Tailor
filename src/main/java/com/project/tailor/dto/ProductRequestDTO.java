package com.project.tailor.dto;

import java.util.List;

import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class ProductRequestDTO {
	
	private Integer id;
	
	
	private String name;
	
	@Size(min=8, max=8, message="code length must be 8")
	private long code;
	
	@Size(max=255, message="The descripption can't be more than 255 careters")
	private String description;
	
	private String photos;
	
	private String color;
	
	private String size;
	
	private String fabric;

	private Integer brandId;
	
	private List<Integer> categoriesId;

	

	
	
}
