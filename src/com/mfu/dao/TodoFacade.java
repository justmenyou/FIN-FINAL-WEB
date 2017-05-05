
package com.mfu.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.mfu.entity.Todo;
import com.mfu.entity.Trips;

public class TodoFacade {

	private static final EntityManagerFactory emfInstance  = Persistence
			.createEntityManagerFactory("transactions-optional");
	private EntityManager em = null;
	
	public TodoFacade() {
		em = emfInstance.createEntityManager();
	}
	
	public List<Todo> getAllTodo() {
		List<Todo> todo = null;
		
		try {
			Query q = em.createQuery("SELECT p FROM Todo p");
			todo = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return todo;
	}
	
	public List<Todo> findTodoByTripKey(String key) {
		List<Todo> todos = null;
		
		try {
			Query q = em.createQuery("select p from Todo p where p.tripKey = :key");
			q.setParameter("key", key);
			todos = q.getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return todos;
	}
	
	public Todo findTodoByKey(String key) {
		Todo todo  = null;
		
		try {
			Query q = em.createQuery("select p from Todo p where p.key=:key");
			q.setParameter("key", key);
			List<Todo> todoList = q.getResultList();
			todo = todoList.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return todo;
	}
	
	public void saveTodo(Todo todo) {
		
		try {
			em.persist(todo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void closeEntityManager() {
		if(em != null) {
			em.close();
		}
	}
	
	public void deleteTodo(Todo todo) {
		if(todo != null) {
			em.remove(todo);
		}
	}
}

