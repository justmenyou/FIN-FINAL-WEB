
package com.mfu.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.mfu.entity.Trips;

public class TripsFacade {

	private static final EntityManagerFactory emfInstance  = Persistence
			.createEntityManagerFactory("transactions-optional");
	private EntityManager em = null;
	
	public TripsFacade() {
		em = emfInstance.createEntityManager();
	}
	
	public List<Trips> getAllTrips() {
		List<Trips> trips = null;
		
		try {
			Query q = em.createQuery("SELECT p FROM Trips p");
			trips = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return trips;
	}
	
	public Trips findTripsByKey(String key) {
		Trips trips  = null;
		
		try {
			Query q = em.createQuery("select p from Trips p where p.key=:key");
			q.setParameter("key", key);
			List<Trips> tripsList = q.getResultList();
			 trips =  tripsList.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return trips;
	}
	
	public List<Trips> findTripsByUserKey(String key) {
		List<Trips> tripsList = null;
		
		try {
			Query q = em.createQuery("select p from Trips p where p.userKey=:key");
			q.setParameter("key", key);
			tripsList = q.getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return tripsList;
	}
	
	public void saveTrips(Trips trips) {
		
		try {
			em.persist(trips);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void closeEntityManager() {
		if(em != null) {
			em.close();
		}
	}
	
	public void deleteTrips(Trips trips) {
		if(trips != null) {
			em.remove(trips);
		}
	}
}

