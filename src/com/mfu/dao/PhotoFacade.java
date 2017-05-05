
package com.mfu.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.mfu.entity.Photo;
import com.mfu.entity.PackingList;

public class PhotoFacade {

	private static final EntityManagerFactory emfInstance = Persistence
			.createEntityManagerFactory("transactions-optional");
	private EntityManager em = null;

	public PhotoFacade() {
		em = emfInstance.createEntityManager();
	}

	public List<Photo> getAllPhoto() {
		List<Photo> item = null;

		try {
			Query q = em.createQuery("SELECT p FROM Photo p");
			item = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return item;
	}


	public Photo findPhotoByKey(String key) {
		Photo item = null;

		try {
			Query q = em.createQuery("select p from Photo p where p.key=:key");
			q.setParameter("key", key);
			List<Photo> itemList = q.getResultList();
			item = itemList.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return item;
	}

	public void savePhoto(Photo item) {

		try {
			em.persist(item);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void closeEntityManager() {
		if (em != null) {
			em.close();
		}
	}

	public void deletePhoto(Photo item) {
		if (item != null) {
			em.remove(item);
		}
	}

	public List<Photo> findPhotoByTripKey(String tripKey) {
		List<Photo> items = null;

		try {
			Query q = em.createQuery("select p from Photo p where p.tripKey=:key");
			q.setParameter("key", tripKey);
			items = q.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return items;
	}
}
