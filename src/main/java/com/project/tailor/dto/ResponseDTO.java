package com.project.tailor.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO {
  
	private String message;
    private String type;
    private Integer status;
}
