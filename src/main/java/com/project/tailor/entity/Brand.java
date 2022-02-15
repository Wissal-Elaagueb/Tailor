package com.project.tailor.entity;

import lombok.*;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Brand {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

    @Column(name="name",unique=true)
	@NotBlank(message = "Brand name can't be blank")
	private String name;
    
    @OneToMany(mappedBy = "brand",
    			cascade= CascadeType.ALL)
    private List<Product> products;


}
