package com.project.tailor.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO {
  
	private String message;
    private String type;
    private Integer status;
    
	public ResponseDTO(String message, String type, Integer status) {
		this.message = message;
		this.type = type;
		this.status = status;
	}
    
    
}
