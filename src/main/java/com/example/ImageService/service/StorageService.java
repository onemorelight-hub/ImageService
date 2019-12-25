package com.example.ImageService.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.ImageService.dao.ImageDao;
import com.example.ImageService.exception.StorageException;

@Service
public class StorageService {

	@Value("${upload.path}")
    private String path;
	
	@Autowired
	private ImageDao imageDao;

    public String uploadFile(MultipartFile file) {

    	String filePath;
    	int id;
        if (file.isEmpty()) {
            throw new StorageException("Failed to store empty file");
        }
        
        id = imageDao.addImage(path);
        System.out.println("Anjan--> ID: "+id);
        try {
           // String fileName = file.getOriginalFilename();
            InputStream is = file.getInputStream();
            Files.copy(is, Paths.get(path + id),StandardCopyOption.REPLACE_EXISTING);
            filePath = path + id ;
        } catch (IOException e) {
            String msg = String.format("Failed to store file", file.getName());
            throw new StorageException(msg, e);
        }
        return filePath;
    }
}
