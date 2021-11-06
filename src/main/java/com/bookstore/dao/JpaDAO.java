package com.bookstore.dao;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class JpaDAO<T> {
	protected EntityManager entityManager;

	public JpaDAO(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}
	
	public T create(T entity) {
		entityManager.getTransaction().begin();
		
		entityManager.persist(entity);
		entityManager.flush();
		entityManager.refresh(entity);
		
		entityManager.getTransaction().commit();
		
		return entity;
	}
	
	public T update(T entity) {
		entityManager.getTransaction().begin();
		entityManager.merge(entity);
		entityManager.getTransaction().commit();
		return entity;
	}
	
	public T find(Class<T> type, Object id) {
		T entity = entityManager.find(type, id);
		if (entity != null) {
			entityManager.refresh(entity);
		}
		
		return entity;
	}
	
	public void delete(Class<T> type, Object id) {
		entityManager.getTransaction().begin();
		
		Object reference = entityManager.getReference(type, id);
		entityManager.remove(reference);
		
		entityManager.getTransaction().commit();
	}
	
	public List<T> findWithNamedQuery(String queryName) {
		Query query = entityManager.createNamedQuery(queryName);
		return query.getResultList();
	}
	
	public List<T> findWithNamedQuery(String queryName, String paramName, Object paramValue) {
		Query query = entityManager.createNamedQuery(queryName);
		
		query.setParameter(paramName, paramValue);
		
		return query.getResultList();
	}
	
	public List<T> findWithNamedQuery(String queryName, Map<String, Object> parameters) {
		Query query = entityManager.createNamedQuery(queryName);
		
		Set<Entry<String, Object>> setParameters = parameters.entrySet();
		
		for (Entry<String, Object> entry: setParameters) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		
		return query.getResultList();
	}
	
	public long countWithNamedQuery(String queryName) {
		Query query = entityManager.createNamedQuery(queryName);
		return (long) query.getSingleResult();
	}
}