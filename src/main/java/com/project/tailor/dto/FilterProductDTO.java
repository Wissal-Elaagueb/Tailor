package com.project.tailor.dto;

import java.util.List;

import lombok.Data;

@Data
public class FilterProductDTO {
	
	private String name;
	
	private String color;
	
	private String size;
	
	private String fabric;

	private Integer brandId;
	
	private List<Integer> categoriesId;
	
	private Integer pageSize;
	
	private Integer pageNumber;
	
}
