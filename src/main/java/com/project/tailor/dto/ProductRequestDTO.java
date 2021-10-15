package com.project.tailor.dto;

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

	private Integer brand_id;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getCode() {
		return code;
	}

	public void setCode(long code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPhotos() {
		return photos;
	}

	public void setPhotos(String photos) {
		this.photos = photos;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getFabric() {
		return fabric;
	}

	public void setFabric(String fabric) {
		this.fabric = fabric;
	}

	public Integer getBrand_id() {
		return brand_id;
	}

	public void setBrand_id(Integer brand_id) {
		this.brand_id = brand_id;
	}
	
	
}
