package com.project.tailor.service;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.project.tailor.entity.File;
import com.project.tailor.exceptionhandeling.BadRequestException;



public interface FileService {
	
	public ResponseEntity<byte[]> findById(Integer id) throws BadRequestException, FileNotFoundException, IOException;
	
	File upload(MultipartFile file) throws BadRequestException, Exception;

	File findFileById(Integer id) throws BadRequestException;

	void delete(Integer id) throws BadRequestException;

	void update(Integer id, File file) throws BadRequestException;

}
