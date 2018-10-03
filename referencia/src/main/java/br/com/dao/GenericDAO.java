package br.com.dao;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import br.com.enums.QueryType;

public class GenericDAO<T> {
	
	protected Class<T> persistentClass;
	
	private Logger LOG = null;
		
	@PersistenceContext(unitName="archetype")
	private EntityManager entityManager;
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public Logger getLogger() {
		if (LOG == null) {
			return Logger.getLogger(this.getClass());
		}
		return LOG;
	}
	
	@SuppressWarnings("unchecked")
	public GenericDAO() {
		getLogger().info("Construtor básico. Obtendo a persistentClass");
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void save(T entity) {
		getLogger().info("Gravando entidade: " + entity);
		this.getEntityManager().persist(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(T entity) {
		getLogger().info("Atualizando entidade: " + entity);
		this.getEntityManager().merge(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	public void delete(T entity) {
		getLogger().info("Excluindo entidade: " + entity);
		this.getEntityManager().remove(this.getEntityManager().merge(entity));
	}

	/**
	 * {@inheritDoc}
	 */
	public T find(Object id) {
		getLogger().info("Buscando entidade de id: " + id);
		return this.getEntityManager().find(this.persistentClass, id);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<T> findAll() {
		getLogger().info("Buscando todas as entidades da classe: " + persistentClass);
		getLogger();
		return find(-1, -1);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<T> find(int firstResult, int maxResults) {
		getLogger().info("Buscando entidades no intervalo  " + firstResult + " a " + maxResults);
		List<T> result = null;
		Query q = this.getEntityManager().createQuery("select obj from "
				+ this.persistentClass.getSimpleName() + " obj");
		setFirstAndMaxResultInQuery(firstResult, maxResults, q);
		result = q.getResultList();
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<T> find(String queryName, Map<String, Object> namedParams) {
		return find(QueryType.NAMED, queryName, namedParams);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<T> find(QueryType type, String query,
			Map<String, Object> namedParams) {
		return find(type, query, namedParams, -1, -1);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<T> find(String queryName, Map<String, Object> namedParams,
			int firstResult, int maxResults) {
		return find(QueryType.NAMED, queryName, namedParams, firstResult,
				maxResults);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<T> find(QueryType type, String query, Map<String, Object> namedParams, int firstResult, int maxResults) {
		List<T> result = null;
		Query q = getQueryObject(type, query);

		// Define os parametros da consulta
		setParamsInQuery(namedParams, q);

		// Define paginação ou não
		setFirstAndMaxResultInQuery(firstResult, maxResults, q);

		// Executa a consulta
		result = q.getResultList();

		return result;
	}

	private void setFirstAndMaxResultInQuery(int firstResult, int maxResults, Query q) {
		if (firstResult >= 0 && maxResults >= 0) {
			q.setFirstResult(firstResult).setMaxResults(maxResults);
		}
	}

	private void setParamsInQuery(Map<String, Object> namedParams, Query q) {
		if (namedParams != null) {
			Set<String> keys = namedParams.keySet();
			for (String key : keys) {
				q.setParameter(key, namedParams.get(key));
			}
		}
	}

	/**
	 * {@inheritDoc} 
	 */
	public T findFirst(String query, Map<String, Object> namedParams) {
		return findFirst(QueryType.NAMED, query, namedParams);
	}

	/**
	 * {@inheritDoc}
	 */
	public T findFirst(QueryType type, String query,
			Map<String, Object> namedParams) {

		List<T> result = find(type, query, namedParams, -1, -1);
		return result == null || (result != null && result.size() == 0) ? null : result.get(0);
	}

	/**
	 * {@inheritDoc}
	 */
	public Object findFirstAnonymous(QueryType type, String query,
			Map<String, Object> namedParams) {
		
		try {
			Query q = getQueryObject(type, query);

			setParamsInQuery(namedParams, q);
			
			return q.getSingleResult();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	private Query getQueryObject(QueryType type, String query) {
		Query q;
		
		if (type == QueryType.JPQL)
			q = this.getEntityManager().createQuery(query);
		else if (type == QueryType.NATIVE)
			q = this.getEntityManager().createNativeQuery(query);
		else if (type == QueryType.NAMED)
			q = this.getEntityManager().createNamedQuery(query);
		else
			throw new IllegalArgumentException("Tipo de Query inválido: " + type);

		return q;
	}
	
	private static <T> T map(Class<T> type, Object[] tuple){
		List<Class<?>> tupleTypes = new ArrayList<>();

		for(Object field : tuple)
			tupleTypes.add(field.getClass());

		try {
			Constructor<T> ctor = type.getConstructor(tupleTypes.toArray(new Class<?>[tuple.length]));

			return ctor.newInstance(tuple);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	
	private static <T> List<T> map(Class<T> type, List<Object[]> records){
		List<T> result = new LinkedList<>();
		for(Object[] record : records){
			result.add(map(type, record));
		}
		return result;
	}

	
	@SuppressWarnings("unchecked")
	public static <T> List<T> getResultList(Query query, Class<T> type) {
		List<Object[]> records = query.getResultList();
		return map(type, records);
	}

	/**
	 * {@inheritDoc}
	 */
	public Long getMaxId() {
		getLogger().info("Buscando o máximo id da entidade: " + persistentClass);
		String consulta = "SELECT MAX(obj.id) FROM " + this.persistentClass.getSimpleName() + " obj";
		Query query = getEntityManager().createQuery(consulta);
		
		return query.getSingleResult() == null ? 1 : (Long) query.getSingleResult() + 1;
	}
}
