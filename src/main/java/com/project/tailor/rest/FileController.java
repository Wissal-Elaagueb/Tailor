package com.project.tailor.rest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.tailor.entity.File;
import com.project.tailor.exceptionhandeling.BadRequestException;
import com.project.tailor.exceptionhandeling.SuccessResponse;
import com.project.tailor.service.FileService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/files")
public class FileController {
	
	private static Logger log = LoggerFactory.getLogger(Slf4j.class);
	
	@Autowired
	private FileService fileService;
	
	
	@GetMapping("/{fileId}")
	public ResponseEntity<SuccessResponse> findFileById (@PathVariable Integer fileId) throws BadRequestException{
		
		log.info("calling method : findFileById()");
		
		MultipartFile file = fileService.findById(fileId);

		SuccessResponse response= new SuccessResponse(file,System.currentTimeMillis());
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	@PostMapping()
	public ResponseEntity<SuccessResponse> uploadFile (@RequestBody MultipartFile file) throws Exception{
		
		log.info("calling method : saveFile()");
		
		File f = fileService.upload(file);
		
		SuccessResponse response= new SuccessResponse(f,System.currentTimeMillis());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}


	
}
