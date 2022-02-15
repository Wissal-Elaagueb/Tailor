package com.project.tailor.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
public class Category {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Integer id;

	
    @Column(name="name",unique=true)
    @NotBlank(message = "Category name can't be blank")
	private String name;

    @NotBlank(message = "Description can't be blank")
   	private String description;

    
   	//mapped by : means the attributes in the other class
    @JsonIgnore
    @Fetch(FetchMode.JOIN)//To avoid LazyInitializationException
   	@ManyToMany( fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinTable(name = "products_categories",
    joinColumns = {
            @JoinColumn(name = "category_id", referencedColumnName = "id",
                    nullable = false)},
    inverseJoinColumns = {
            @JoinColumn(name = "product_id", referencedColumnName = "id",
                    nullable = false)})
    private Set<Product> products = new HashSet<Product>();
   	

	public Category(String name, String description) {
		this.name = name;
		this.description = description;
	}
   	
   	
}
