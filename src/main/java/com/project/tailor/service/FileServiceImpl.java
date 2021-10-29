package com.project.tailor.service;

import java.io.FileOutputStream;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.tailor.dao.FileRepository;
import com.project.tailor.entity.File;
import com.project.tailor.exceptionhandeling.BadRequestException;

@Service
public class FileServiceImpl implements FileService {

	@Autowired
	private FileRepository fileRepository;
	
	@Override
	public File upload(MultipartFile file) throws Exception {
		 
		if (!file.getContentType().equals("image/png") && !file.getContentType().equals("image/jpeg"))
			throw new BadRequestException("Image must be in png or jpeg format");
		
		java.io.File f = new java.io.File("Product Images");
		f.mkdir();
		
		File savedFile = new File();
		savedFile.setOriginalName(file.getOriginalFilename());
		savedFile.setContentType(file.getContentType());
		savedFile.setFileName("until I get ààId");
		savedFile.setPath("Product Images/"+savedFile.getFileName()+"."+file.getContentType().substring(6));
		
		java.io.File convFile = new java.io.File (savedFile.getPath());
		convFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		
		return fileRepository.save(savedFile);
	}
	
	@Override
	public MultipartFile findById(Integer id) throws BadRequestException {
		Optional<File> file = fileRepository.findById(id);
	     
		if (file.isEmpty()) 
			throw new BadRequestException("File not found - "+id);
		
		File savedFile = file.get();
       
		java.io.File convFile = new java.io.File (savedFile.getPath());
		
		//return file.get();
		return null;
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
		findById(id);	
		file.setId(id);
		fileRepository.save(file);
	}
	

}
