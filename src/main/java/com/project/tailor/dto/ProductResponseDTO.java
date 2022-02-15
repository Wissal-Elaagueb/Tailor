package com.project.tailor.dto;

import java.util.ArrayList;
import java.util.List;

import com.project.tailor.entity.Category;
import com.project.tailor.entity.File;
import com.project.tailor.entity.Product;

public class ProductResponseDTO {
	
	private String name;
	
	private long code;
	
	private String description;
	
	private List<File> images;
	
	private String color;
	
	private String size;
	
	private String fabric;
	
	private Integer brandId;
	
	private ArrayList<Integer> categoriesId= new ArrayList<Integer>();
	
	
	public ProductResponseDTO(Product p) {
		
		name=p.getName();
		code=p.getCode();
		description=p.getDescription();
		images=p.getImages();
		color=p.getColor();
		size=p.getSize();
		fabric=p.getFabric();
		if(p.getBrand() != null) 
			brandId=p.getBrand().getId();
		else
			brandId=null;
		if (p.getCategories() != null) {
			for (Category c : p.getCategories())
				categoriesId.add(c.getId());
		}
		else
			categoriesId = null;	
	}


	@Override
	public String toString() {
		return "ProductResponseDTO [name=" + name + ", code=" + code + ", description=" + description + ", images="
				+ images + ", color=" + color + ", size=" + size + ", fabric=" + fabric + ", brandId=" + brandId
				+ ", categoriesId=" + categoriesId + "]";
	}
	
	
}
