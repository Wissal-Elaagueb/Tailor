package com.project.tailor.service;

import java.util.List;
import java.util.Optional;

import com.project.tailor.exceptionhandeling.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.project.tailor.dao.BrandRepository;
import com.project.tailor.dto.BrandRequestDTO;
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
	public Brand findById(Integer id) throws BadRequestException {
		Optional<Brand> brand = brandRepository.findById(id);

		if (brand.isEmpty())
			throw new BadRequestException("Brand not found - "+id);

		return brand.get();
	}


	@Override
	public void save(BrandRequestDTO brand) {
		Brand finalBrand= new Brand(brand);
		brandRepository.save(finalBrand);
	}
	
	@Override
	public void update(Integer id, BrandRequestDTO brand) throws BadRequestException {
		findById(id);
		Brand finalBrand = new Brand(brand);
		finalBrand.setId(id);
		brandRepository.save(finalBrand);
	}

	@Override
	public Brand deleteById(Integer id) throws BadRequestException {
		Brand brand = findById(id);
		brandRepository.deleteById(id);
		return brand;
	}

}

