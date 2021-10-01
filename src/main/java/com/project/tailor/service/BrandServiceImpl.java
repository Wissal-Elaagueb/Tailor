package com.project.tailor.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.tailor.dao.BrandRepository;
import com.project.tailor.entity.Brand;

@Service
public class BrandServiceImpl implements BrandService {

	
	@Autowired
	private BrandRepository brandRepository;
	
	
	
	@Override
	public List<Brand> findAll() {
		return brandRepository.findAll();
	}

	
	
	@Override
	public Brand findById(int id) {
		Optional<Brand> result = brandRepository.findById(id);
		
        Brand brand = null;
        
        if (result.isPresent()) {
       	 brand = result.get();
        }
        else {
       	 throw new RuntimeException("Did not find employe id - "+id);
        }
        
        return brand;
	}

	
	@Override
	public void save(Brand brand) {
		brandRepository.save(brand);
	}

	@Override
	public void deleteById(int id) {
		brandRepository.deleteById(id);
	}

}

