package com.project.tailor.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.tailor.entity.Brand;

public interface BrandRepository extends JpaRepository<Brand, Integer> {

}
