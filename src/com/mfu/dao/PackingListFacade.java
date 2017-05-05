
package com.mfu.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.mfu.entity.PackingList;

public class PackingListFacade {

	private static final EntityManagerFactory emfInstance  = Persistence
			.createEntityManagerFactory("transactions-optional");
	private EntityManager em = null;
	
	public PackingListFacade() {
		em = emfInstance.createEntityManager();
	}
	
	public List<PackingList> getAllPackingList() {
		List<PackingList> packingList = null;
		
		try {
			Query q = em.createQuery("SELECT p FROM PackingList p");
			packingList = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return packingList;
	}
	
	public PackingList findPackingListByKey(String key) {
		PackingList packingList  = null;
		
		try {
			Query q = em.createQuery("select p from PackingList p where p.key=:key");
			q.setParameter("key", key);
			List<PackingList> packingListList = q.getResultList();
			packingList = packingListList.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return packingList;
	}
	

	public List<PackingList> findPackingListByTripKey(String key) {
		List<PackingList> packingList  = null;
		
		try {
			Query q = em.createQuery("select p from PackingList p where p.tripKey=:key");
			q.setParameter("key", key);
			packingList = q.getResultList();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return packingList;
	}
	
	public void savePackingList(PackingList packingList) {
		
		try {
			em.persist(packingList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void closeEntityManager() {
		if(em != null) {
			em.close();
		}
	}
	
	public void deletePackingList(PackingList packingList) {
		if(packingList != null) {
			em.remove(packingList);
		}
	}
}

