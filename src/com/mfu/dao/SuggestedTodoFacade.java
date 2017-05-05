
package com.mfu.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import com.mfu.entity.SuggestedTodo;

public class SuggestedTodoFacade {

	private static final EntityManagerFactory emfInstance = Persistence
			.createEntityManagerFactory("transactions-optional");
	private EntityManager em = null;

	public SuggestedTodoFacade() {
		em = emfInstance.createEntityManager();
	}

	public List<SuggestedTodo> getAllSuggestedTodo() {
		List<SuggestedTodo> SuggestedTodo = null;

		try {
			Query q = em.createQuery("SELECT p FROM SuggestedTodo p");
			SuggestedTodo = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return SuggestedTodo;
	}

	public SuggestedTodo findSuggestedTodoByKey(String key) {
		SuggestedTodo suggestedTodo = null;

		try {
			Query q = em.createQuery("select p from SuggestedTodo p where p.key=:key");
			q.setParameter("key", key);
			List<SuggestedTodo> SuggestedTodoList = q.getResultList();
			suggestedTodo = SuggestedTodoList.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return suggestedTodo;
	}

	public void saveSuggestedTodo(SuggestedTodo suggestedTodo) {

		try {
			em.persist(suggestedTodo);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void closeEntityManager() {
		if (em != null) {
			em.close();
		}
	}

	public void deleteSuggestedTodo(SuggestedTodo suggestedTodo) {
		if (suggestedTodo != null) {
			em.remove(suggestedTodo);
		}
	}

	public List<SuggestedTodo> findSuggestedToDoBySuggestTripKey(String tripKey) {
		List<SuggestedTodo> suggestedTodo = null;

		try {
			Query q = em.createQuery("select p from SuggestedTodo p where p.suggestedTripsKey=:key");
			q.setParameter("key", tripKey);
			suggestedTodo = q.getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return suggestedTodo;
	}
}
