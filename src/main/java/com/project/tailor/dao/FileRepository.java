package com.project.tailor.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.tailor.entity.File;


public interface FileRepository extends JpaRepository<File, Integer>{

}
