package com.project.tailor.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Category {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Integer id;

    @Column(name="name",unique=true)	
	private String name;

   	private String description;

   	//mapped by : means the attributes in the other class
   	@ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
    private Set<Product> products = new HashSet<>();
   	

	public Category(String name, String description) {
		this.name = name;
		this.description = description;
	}
   	
   	
}
