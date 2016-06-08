package br.gov.ce.tce.domain.dao.sca.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.gov.ce.tce.domain.dao.sca.UsuarioDAO;
import br.gov.ce.tce.domain.entity.sca.Usuario;


@Repository
public class UsuarioDAOImpl implements UsuarioDAO {
	
	static Logger logger = Logger.getLogger(UsuarioDAOImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void setEntityManager(EntityManager entityManager){
		this.entityManager = entityManager;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> findAll() {
		Query query = entityManager.createNamedQuery("Usuario.findAll");
		return query.getResultList();
	}

	@Override
	public Usuario findById(Long id) {
		try {
			Query query = entityManager.createNamedQuery("Usuario.findById");
			query.setParameter("id", id);
			return (Usuario) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Usuario findByUsername(String nome) {
		try {
			Query query = entityManager.createNamedQuery("Usuario.findByUsername");
			query.setParameter("username", nome);
			return (Usuario) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> findByNome(String nome) {
		try {
			Query query = entityManager.createNamedQuery("Usuario.findByNome");
			query.setParameter("nome", "%" + nome.toUpperCase() + "%");
			return query.getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Usuario findByUsuario(Usuario usuario) {
		return findById(usuario.getId());
	}

	@Override
	public void salvar(Usuario usuario) {
		//verifica se é um novo usuário ou se é apenas uma alteração 
		if(usuario.getId() == null || usuario.getId() == 0) {
			usuario.setId(getMaxId(usuario.getTipo()));
		}
		
		//salvando na tabela FWUser do schema SAPJAVA, se o usuario for interno(tipo 1)
//		if(usuario.getTipo() == 1) {
//			daoFwuser.salvar(usuario);
//		}
		
		entityManager.merge(usuario);
	}

	@Override
	public Usuario findByCpf(String cpf) {
		try {
			Query query = entityManager.createNamedQuery("Usuario.findByCpf");
			query.setParameter("cpf", cpf);
			return (Usuario) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
	
	private Long getMaxId(int tipo) {
		Query query;
		String consulta; 
		if(tipo == 1) {
			consulta = new String("SELECT MAX(u.id) FROM Usuario u WHERE u.tipo = 1 AND u.id <> 3001");
			query = entityManager.createQuery(consulta);
		}
		else {
			consulta = new String("SELECT MAX(u.id) FROM Usuario u");
			query = entityManager.createQuery(consulta);
		}
		return query.getSingleResult() == null ? 1 : (Long) query.getSingleResult() + 1;
	}

}
