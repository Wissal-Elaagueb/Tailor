package com.project.tailor.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.*;


@Entity
@Table(name="brand")
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

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	

}
