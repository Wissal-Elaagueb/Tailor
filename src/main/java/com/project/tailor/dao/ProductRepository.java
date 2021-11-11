package com.project.tailor.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.tailor.entity.Product;


public interface ProductRepository extends JpaRepository<Product, Integer> {
	 
	
	@Query("select p from Product p "
			+ "where p.name like ?1 and p.color Like ?2 "
			+ "and p.size like ?3 and p.fabric like ?4 ")
	public List<Product> filter(String name, String color, String size, String fabric,Pageable page);
	
	
	@Query("select p from Product p "
			+ "where p.name like ?1 and p.color Like ?2 "
			+ "and p.size like ?3 and p.fabric like ?4 "
			+ "and p.brand.id  like ?5")
	public List<Product> filter(String name, String color, String size, String fabric, Integer brandId, Pageable page);
	
	
	
	
}
