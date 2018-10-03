package br.com.dao.acesso;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.entity.acesso.Usuario;

@Repository
public class UsuarioDAO {
	
	static Logger logger = Logger.getLogger(UsuarioDAO.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void setEntityManager(EntityManager entityManager){
		this.entityManager = entityManager;
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> findAll() {
		Query query = entityManager.createQuery("SELECT u FROM Usuario u ORDER BY u.id");

		return query.getResultList();
	}

	public Usuario findById(Long id) {
		try {
			Query query = entityManager.createQuery("SELECT u FROM Usuario u WHERE u.id = :id");
			query.setParameter("id", id);

			return (Usuario) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Usuario findByUsername(String nome) {
		try {
			Query query = entityManager.createQuery("SELECT usu FROM Usuario usu WHERE UPPER(usu.username) = UPPER(:username)");
			query.setParameter("username", nome);

			return (Usuario) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> findByNome(String nome) {
		try {
			Query query = entityManager.createQuery("SELECT usu FROM Usuario usu WHERE UPPER( usu.nome ) LIKE :nome ORDER BY usu.nome");
			query.setParameter("nome", "%" + nome.toUpperCase() + "%");

			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Usuario findByUsuario(Usuario usuario) {
		return findById(usuario.getId());
	}

	public Usuario findByCpf(String cpf) {
		try {
			Query query = entityManager.createNamedQuery("Usuario.findByCpf");
			query.setParameter("cpf", cpf);

			return (Usuario) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
	
}
