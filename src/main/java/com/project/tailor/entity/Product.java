package com.project.tailor.entity;

import java.util.ArrayList;
import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.tailor.dto.ProductRequestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="name",unique=true)
	private String name;
	
	@Column(name="code",unique=true)
	private long code;
	
	@Column
	private String description;
	
	@OneToMany(mappedBy = "product"
			// if there is cascade Type: it dosen't delete
			,cascade = CascadeType.ALL
			)
	private List<File> images;
	
	@Column
	private String color;
	
	@Column
	private String size;
	
	@Column
	private String fabric;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="brand_id")
	private Brand brand;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "products_categories",
            joinColumns = {
                    @JoinColumn(name = "product_id", referencedColumnName = "id",
                            nullable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "category_id", referencedColumnName = "id",
                            nullable = false)})
	private List<Category> categories= new ArrayList<>();
	
	
	
	
	public Product(ProductRequestDTO product, Brand brand, List<Category> categories) {
		this.name=product.getName();
		this.code=product.getCode();
		this.description=product.getDescription();
		//this.images=images;
		this.color=product.getColor();
		this.size=product.getSize();
		this.fabric=product.getFabric();
		this.brand=brand;
		this.categories.addAll(categories);
	}

}
