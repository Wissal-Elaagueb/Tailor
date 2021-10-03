package com.project.tailor.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.tailor.entity.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
