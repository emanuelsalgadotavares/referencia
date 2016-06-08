package br.gov.ce.tce.domain.dao;

import java.util.List;
import java.util.Map;

import br.gov.ce.tce.domain.enums.QueryType;


public interface GenericDAO<T> {
	
	public void save(T entity);
	
	public void update(T entity);

	public void delete(T entity);

	public T find(Object id);

	public List<T> find(int firstResult, int maxResults);

	public List<T> find(String queryName, Map<String, Object> namedParams);

	public List<T> find(QueryType type, String query, Map<String, Object> namedParams);

	public List<T> find(String queryName, Map<String, Object> namedParams, 
			int firstResult, int maxResults);

	public List<T> find(QueryType type, String query, Map<String, Object> namedParams, 
			int firstResult, int maxResults);

	public T findFirst(String query, Map<String, Object> namedParams);
	
	public T findFirst(QueryType type, String query, Map<String, Object> namedParams);

	public Object findFirstAnonymous(QueryType type, String query, Map<String, Object> namedParams);

	public List<T> findAll();
	
	public Long getMaxId();
}
