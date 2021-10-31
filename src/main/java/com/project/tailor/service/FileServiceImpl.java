package com.project.tailor.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;
import org.springframework.http.HttpHeaders;


import com.project.tailor.dao.FileRepository;
import com.project.tailor.entity.File;
import com.project.tailor.exceptionhandeling.BadRequestException;

@Service
public class FileServiceImpl implements FileService {

	@Autowired
	private FileRepository fileRepository;
	
	@Override
	public File upload(MultipartFile file) throws BadRequestException, IOException  {
		 
		if (!file.getContentType().equals("image/png") && !file.getContentType().equals("image/jpeg"))
			throw new BadRequestException("Image must be in png or jpeg format");
		
		java.io.File f = new java.io.File("ProductImages");
		f.mkdir();
		
		File savedFile = new File();
		savedFile.setOriginalName(file.getOriginalFilename());
		savedFile.setContentType(file.getContentType());
		savedFile.setFileName(generateRandomString());
		savedFile.setPath("ProductImages/"+savedFile.getFileName()+"."+file.getContentType().substring(6));
		
		java.io.File convFile = new java.io.File (savedFile.getPath());
		convFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		
		return fileRepository.save(savedFile);
	}
	
	@Override
	public ResponseEntity<byte[]> findById(Integer id) throws BadRequestException, IOException {
		Optional<File> file = fileRepository.findById(id);
	     
		if (file.isEmpty()) 
			throw new BadRequestException("File not found - "+id);
		
		File savedFile = file.get();
       
		java.io.File convFile = new java.io.File (savedFile.getPath());
		FileInputStream fis = new FileInputStream(convFile);
		byte[] image = fis.readAllBytes();
		
		return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.get().getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.get().getOriginalName() + "\"")
                .body(image);
	}
	
	
	@Override
	public File findFileById(Integer id) throws BadRequestException {
		Optional<File> file = fileRepository.findById(id);
	     
		if (file.isEmpty()) 
			throw new BadRequestException("File not found - "+id);
		
		return file.get();
	}

	@Override
	public void update(Integer id, File file) throws BadRequestException {
		findFileById(id);	
		file.setId(id);
		fileRepository.save(file);
	}
	
	
	public String generateRandomString() {
		int leftLimit = 48; // numeral '0'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 10;
	    Random random = new Random();

	    String generatedString = random.ints(leftLimit, rightLimit + 1)
	      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
	      .limit(targetStringLength)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();
		return generatedString;
	}
	

}
