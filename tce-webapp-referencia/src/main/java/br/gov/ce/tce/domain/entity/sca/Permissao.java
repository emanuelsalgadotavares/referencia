package br.gov.ce.tce.domain.entity.sca;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.gov.ce.tce.domain.entity.BasicEntity;


@Entity
@Table(name="PERMISSAO", schema="SCA")
@NamedQueries({
	@NamedQuery(name = "Permissao.findAll", query = "SELECT s FROM Permissao s ORDER BY s.id"),
	@NamedQuery(name = "Permissao.findMaxId", query = "SELECT MAX(s.id) FROM Permissao s"),
	@NamedQuery(name = "Permissao.findById", query = "SELECT s FROM Permissao s WHERE s.id = :id"),
	@NamedQuery(name = "Permissao.findBySistema", query = "SELECT p FROM Permissao p WHERE p.sistema = :sistema"),
	@NamedQuery(name = "Permissao.findByGrupoAndSistemaSAP", query = "SELECT p FROM Permissao p WHERE p.grupo = :grupo AND p.sistema.id = :sistema"),
	@NamedQuery(name = "Permissao.findByGrupoAndUsuarioAndSistemaSAP", query = "SELECT p FROM Permissao p WHERE p.grupo = :grupo AND p.usuario = :usuario AND p.sistema.id = :sistema"),
	@NamedQuery(name = "Permissao.findByDistinctGrupoAndSistemaSAP", query = "SELECT distinct p.secao FROM Permissao p WHERE p.grupo = :grupo AND p.sistema.id = :sistema ORDER BY p.secao.ordem"),
	@NamedQuery(name = "Permissao.findSecaoBySistema", query = "SELECT p.secao FROM Permissao p WHERE p.sistema = :sistema"),
	@NamedQuery(name = "Permissao.findGrupoBySistema", query = "SELECT p.grupo FROM Permissao p WHERE p.sistema = :sistema"),
	@NamedQuery(name = "Permissao.findSecaoByGrupo", query = "SELECT p.secao FROM Permissao p WHERE p.grupo = :grupo AND p.sistema= :sistema ORDER BY p.secao"),
	@NamedQuery(name = "Permissao.findBySistemaGrupoSecao", query = "SELECT p FROM Permissao p WHERE p.sistema = :sistema AND p.grupo = :grupo AND p.secao = :secao")
})

public class Permissao extends BasicEntity<Long> implements Serializable{

	//construtor default requerido pelo hibernate	
	public Permissao() {
		super();
	}

	public Permissao(Long id, Sistema sistema, Grupo grupo, Secao secao, Usuario usuario) {	
		this.id = id;
		this.sistema = sistema;
		this.grupo = grupo;
		this.secao = secao;
		this.usuario = usuario;
	}

	private static final long serialVersionUID = 1L;


	@Id
	@Column(name="ID")
	private Long id;

	@ManyToOne(fetch=FetchType.LAZY) 
	@JoinColumn(name = "SISTEMA")	
	private Sistema sistema;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="GRUPO")		
	private Grupo grupo;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="USUARIO", nullable = true)		
	private Usuario usuario;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SECAO")
	private Secao secao;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Sistema getSistema() {
		return sistema;
	}

	public void setSistema(Sistema sistema) {
		this.sistema = sistema;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public Secao getSecao() {
		return secao;
	}

	public void setSecao(Secao secao) {
		this.secao = secao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	

}
