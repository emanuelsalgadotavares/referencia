package br.com.model.acesso;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.model.BasicEntity;

@Entity
public class UserRole extends BasicEntity<Long> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID")
	private Long id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idgroup")		
	private Group grupo;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="iduser", nullable = true)		
	private User usuario;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idrole")
	private Role secao;

	/* CONSTRUTORES */
	public UserRole() {
		super();
	}

	public UserRole(Long id, Group grupo, Role secao, User usuario) {	
		this.id = id;
		this.grupo = grupo;
		this.secao = secao;
		this.usuario = usuario;
	}

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

	public Role getSecao() {
		return secao;
	}
	public void setSecao(Role secao) {
		this.secao = secao;
	}

}
