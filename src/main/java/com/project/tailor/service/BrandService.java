package com.project.tailor.service;

import java.util.List;

import javax.validation.Valid;

import com.project.tailor.dto.BrandRequestDTO;
import com.project.tailor.entity.Brand;
import com.project.tailor.exceptionhandeling.BadRequestException;



public interface BrandService {

	public List<Brand> findAll();
	
	public Brand findById(Integer id) throws BadRequestException;
	
	public void save(@Valid BrandRequestDTO brand);
	
	void update(Integer brandId, @Valid BrandRequestDTO brand) throws BadRequestException;
	
	public Brand deleteById(Integer id) throws BadRequestException;


	
}
