package br.com.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.model.acesso.Role;
import br.com.model.acesso.User;

@Repository
public class RoleDAO extends GenericDAO<Role> {

	@SuppressWarnings("unchecked")
	public List<Role> findRoleListByUser(User user) {
		
		StringBuilder consulta = new StringBuilder("SELECT secao.* FROM SECAO secao WHERE secao.ID IN ");
		
		consulta.append("(");
		
		// CONSULTA TODAS AS PERMISSOES DOS GRUPOS QUE O USUARIO ESTA INSERIDO
		consulta.append("SELECT DISTINCT(s.ID) FROM USUARIO u ");
		consulta.append("INNER JOIN GRUPOUSUARIO gu ON u.ID = gu.USUARIO ");
		consulta.append("INNER JOIN GRUPO g ON gu.GRUPO = g.ID ");
		consulta.append("INNER JOIN PERMISSAO p ON g.ID = p.GRUPO ");
		consulta.append("INNER JOIN SECAO s ON p.SECAO = s.ID ");
		consulta.append("WHERE u.id = :usuarioId");
		
		consulta.append("UNION ALL ");
		
		// CONSULTA TODAS AS PERMISSOES QUE O USUARIO POSSUI
		consulta.append("SELECT DISTINCT(s.ID) FROM USUARIO u ");
		consulta.append("INNER JOIN PERMISSAO p ON u.ID = p.USUARIO ");
		consulta.append("INNER JOIN SECAO s ON p.SECAO = s.ID ");
		consulta.append("WHERE u.id = :usuarioId");
		
		consulta.append(")");
		
		Query query = getEntityManager().createNativeQuery(consulta.toString(), Role.class);
		query.setParameter("usuarioId", user.getId());
		
		return query.getResultList();
	}
	
}
