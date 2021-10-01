package com.project.tailor.service;

import java.util.List;

import com.project.tailor.entity.Brand;



public interface BrandService {

	public List<Brand> findAll();
	
	public Brand findById(int id);
	
	public void save(Brand brand);
	
	public void deleteById(int id);
}
