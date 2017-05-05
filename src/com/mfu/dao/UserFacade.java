package com.mfu.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.mfu.entity.User;

public class UserFacade {

	private static final EntityManagerFactory emfInstance  = Persistence
			.createEntityManagerFactory("transactions-optional");
	private EntityManager em = null;
	
	public UserFacade() {
		em = emfInstance.createEntityManager();
	}
	
	public List<User> getAllUser() {
		List<User> user = null;
		
		try {
			Query q = em.createQuery("SELECT p FROM User p");
			user = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	public User findUserByKey(String key) {
		User user  = null;
		
		try {
			Query q = em.createQuery("select p from User p where p.key=:key");
			q.setParameter("key", key);
			List<User> userList = q.getResultList();
			user = userList.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	public void saveUser(User user) {
		
		try {
			em.persist(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void closeEntityManager() {
		if(em != null) {
			em.close();
		}
	}
	
	public void deleteUser(User user) {
		if(user != null) {
			em.remove(user);
		}
	}
}

