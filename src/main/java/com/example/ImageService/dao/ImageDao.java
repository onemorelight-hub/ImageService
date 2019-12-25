package com.example.ImageService.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface ImageDao {
	public int addImage(String location);
	public boolean updateImage(String location);
	public boolean deleteImage(int imageId);
	public String getImageLocation(int imageId);
}
