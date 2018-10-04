package br.com.model.acesso;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.BatchSize;

import br.com.model.BasicEntity;

@Entity
@BatchSize(size=1000)
public class GroupUser extends BasicEntity<Long> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	@ManyToOne(fetch=FetchType.LAZY) 
	@JoinColumn(name = "idgroup")	
	private Group grupo;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="iduser")	
	private User usuario;

	/* GET & SET */
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Group getGrupo() {
		return grupo;
	}
	public void setGrupo(Group grupo) {
		this.grupo = grupo;
	}

	public User getUsuario() {
		return usuario;
	}
	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}

}
