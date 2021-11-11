package com.project.tailor.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.project.tailor.entity.File;


public interface FileRepository extends JpaRepository<File, Integer>{

	@Modifying
	@Query("delete from File where id=?1")
	public void deleteWithId(Integer id);
}
