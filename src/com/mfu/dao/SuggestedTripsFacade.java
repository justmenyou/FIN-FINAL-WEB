
package com.mfu.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.mfu.entity.SuggestedTrips;

public class SuggestedTripsFacade {

	private static final EntityManagerFactory emfInstance  = Persistence
			.createEntityManagerFactory("transactions-optional");
	private EntityManager em = null;
	
	public SuggestedTripsFacade() {
		em = emfInstance.createEntityManager();
	}
	
	public List<SuggestedTrips> getAllSuggestedTrips() {
		List<SuggestedTrips> suggestedTrips = null;
		
		try {
			Query q = em.createQuery("SELECT p FROM SuggestedTrips p");
			suggestedTrips = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return suggestedTrips;
	}
	
	public SuggestedTrips findSuggestedTripsByKey(String key) {
		SuggestedTrips suggestedTrips  = null;
		
		try {
			Query q = em.createQuery("select p from SuggestedTrips p where p.key=:key");
			q.setParameter("key", key);
			List<SuggestedTrips> suggestedTripsList = q.getResultList();
			suggestedTrips = suggestedTripsList.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return suggestedTrips;
	}
	
	public void saveSuggestedTrips(SuggestedTrips suggestedTrips) {
		
		try {
			em.persist(suggestedTrips);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void closeEntityManager() {
		if(em != null) {
			em.close();
		}
	}
	
	public void deleteSuggestedTrips(SuggestedTrips suggestedTrips) {
		if(suggestedTrips != null) {
			em.remove(suggestedTrips);
		}
	}
}

