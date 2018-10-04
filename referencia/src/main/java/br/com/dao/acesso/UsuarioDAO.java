package br.com.dao.acesso;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.model.acesso.User;

@Repository
public class UsuarioDAO {
	
	static Logger logger = Logger.getLogger(UsuarioDAO.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void setEntityManager(EntityManager entityManager){
		this.entityManager = entityManager;
	}
	
	@SuppressWarnings("unchecked")
	public List<User> findAll() {
		Query query = entityManager.createQuery("SELECT u FROM Usuario u ORDER BY u.id");

		return query.getResultList();
	}

	public User findById(Long id) {
		try {
			Query query = entityManager.createQuery("SELECT u FROM Usuario u WHERE u.id = :id");
			query.setParameter("id", id);

			return (User) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public User findByUsername(String nome) {
		try {
			Query query = entityManager.createQuery("SELECT usu FROM Usuario usu WHERE UPPER(usu.username) = UPPER(:username)");
			query.setParameter("username", nome);

			return (User) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<User> findByNome(String nome) {
		try {
			Query query = entityManager.createQuery("SELECT usu FROM Usuario usu WHERE UPPER( usu.nome ) LIKE :nome ORDER BY usu.nome");
			query.setParameter("nome", "%" + nome.toUpperCase() + "%");

			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	public User findByUsuario(User usuario) {
		return findById(usuario.getId());
	}

}
