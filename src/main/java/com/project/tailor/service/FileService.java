package com.project.tailor.service;

import org.springframework.web.multipart.MultipartFile;

import com.project.tailor.entity.File;
import com.project.tailor.exceptionhandeling.BadRequestException;



public interface FileService {
	
	public MultipartFile findById(Integer id) throws BadRequestException;
	
	File upload(MultipartFile file) throws BadRequestException, Exception;

	File findFileById(Integer id) throws BadRequestException;

	void update(Integer id, File file) throws BadRequestException;

}
