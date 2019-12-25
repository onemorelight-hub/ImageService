package com.example.ImageService.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.ImageService.model.Image;

@Repository
public class ImageDaoImpl implements ImageDao{

    private static final Logger LOGGER = LoggerFactory.getLogger(ImageDaoImpl.class);

	@Autowired
    private SessionFactory sessionFactory;

	
	@Override
	public int addImage(String location) {
		int id = 0;
		Session session = sessionFactory.openSession();
		Image image = new Image();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			image.setLocation(location);
			id = (int) session.save(image);
			tx.commit();
			LOGGER.info(" Image added id: {}",id);
	 } catch (HibernateException e) {
        if (tx!=null) tx.rollback();
		LOGGER.error("signUpUser(): Exception in user signup! email: {}");
        e.printStackTrace(); 
      } finally {
         session.close(); 
      }		
		return id;
	}

	@Override
	public boolean updateImage(String location) {
		return false;
	}

	@Override
	public boolean deleteImage(int imageId) {
		return false;
	}

	@Override
	public String getImageLocation(int imageId) {
		return null;
	}

}
