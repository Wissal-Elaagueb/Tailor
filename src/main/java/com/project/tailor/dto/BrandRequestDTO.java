package com.project.tailor.dto;



import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


import lombok.Data;

@Data
public class BrandRequestDTO {

		@NotBlank(message = "Brand name can't be blank")
		private String name;
		
		@Size(max=255, message="The descripption can't be more than 255 carecters")
		@NotBlank(message="Product description can't be blank")
		private String description;
}
