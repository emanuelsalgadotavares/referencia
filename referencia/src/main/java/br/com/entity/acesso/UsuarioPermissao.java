package br.com.entity.acesso;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.model.BasicEntity;

@Entity
public class UsuarioPermissao extends BasicEntity<Long> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID")
	private Long id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="GRUPO")		
	private Grupo grupo;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="USUARIO", nullable = true)		
	private Usuario usuario;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SECAO")
	private Permissao secao;

	/* CONSTRUTORES */
	public UsuarioPermissao() {
		super();
	}

	public UsuarioPermissao(Long id, Grupo grupo, Permissao secao, Usuario usuario) {	
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

	public Grupo getGrupo() {
		return grupo;
	}
	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public Permissao getSecao() {
		return secao;
	}
	public void setSecao(Permissao secao) {
		this.secao = secao;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
