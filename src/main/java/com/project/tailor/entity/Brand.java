package com.project.tailor.entity;

import lombok.*;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.*;


@Entity
@Data //Getters setters and hashcode
@NoArgsConstructor
@AllArgsConstructor
public class Brand {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

    @Column(name="name",unique=true)
	@NotBlank(message = "Manhebouch null")
	private String name;
    
    @OneToMany(mappedBy = "brand",
    			cascade= CascadeType.ALL)
    private List<Product> products;

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id=id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name=name;
	}

	/*
	public List<Product> getProducts() {
		return products;
	}


	public void setProducts(List<Product> products) {
		this.products = products;
	}*/
	
}
