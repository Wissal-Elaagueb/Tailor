package com.project.tailor.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.tailor.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
