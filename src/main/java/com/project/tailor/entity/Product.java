package com.project.tailor.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data //Getters setters and hashcode
@NoArgsConstructor
@AllArgsConstructor
public class Product {

	public Product() {
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="name",unique=true)
	@NotBlank(message = "Manhebouch null")
	private String name;
	
	@Column(name="code",unique=true)
	private long code;
	
	@Column
	private String description;
	
	@Column
	private String photos;
	
	@Column
	private String color;
	
	@Column
	private String size;
	
	@Column
	private String fabric;

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

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", code=" + code + ", description=" + description + ", photos="
				+ photos + ", color=" + color + ", size=" + size + ", fabric=" + fabric + "]";
	}
	
	
	
	
}
