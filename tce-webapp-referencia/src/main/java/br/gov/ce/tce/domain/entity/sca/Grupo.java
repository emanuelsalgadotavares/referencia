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
@Table(name="GRUPO", schema="SCA")
@NamedQueries({
	@NamedQuery(name = "Grupo.findMaxId", query = "SELECT MAX(g.id) FROM Grupo g"),
	@NamedQuery(name = "Grupo.findByGrupo", query = "SELECT g FROM Grupo g WHERE g.id = :id"),
	@NamedQuery(name = "Grupo.findByNome", query = "SELECT g FROM Grupo g WHERE UPPER(g.nome) LIKE :nome"),
	@NamedQuery(name = "Grupo.findById", query = "SELECT g FROM Grupo g WHERE g.id = :id"),
	@NamedQuery(name = "Grupo.countByNome", query = "SELECT count(g) FROM Grupo g WHERE UPPER( g.nome ) LIKE :nome ORDER BY g.nome"),
	@NamedQuery(name = "Grupo.searchByNome", query = "SELECT g FROM Grupo g JOIN FETCH g.sistema WHERE UPPER( g.nome ) LIKE :nome ORDER BY g.nome"),
	@NamedQuery(name = "Grupo.findAll", query = "SELECT g FROM Grupo g ORDER BY g.id"),
	@NamedQuery(name = "Grupo.findBySistema", query = "SELECT g FROM Grupo g WHERE g.sistema = :sistema"),
	@NamedQuery(name = "Grupo.count", query = "SELECT count(g) FROM Grupo g WHERE UPPER( g.nome ) LIKE :nome AND g.sistema.sigla LIKE :sigla"),
	@NamedQuery(name = "Grupo.searchByNomeAndSistema", query = "SELECT g FROM Grupo g INNER JOIN g.sistema s WHERE s.id = :sistema AND UPPER( g.nome ) LIKE :nome ORDER BY g.nome"),
	@NamedQuery(name = "Grupo.searchBySiglaNome", query = "SELECT g FROM Grupo g WHERE UPPER( g.nome ) LIKE :nome AND g.sistema.sigla LIKE :sigla ORDER BY g.nome")
})
public class Grupo extends BasicEntity<Long> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	private Long id;

	@ManyToOne(fetch=FetchType.EAGER) 
	@JoinColumn(name = "SISTEMA")	
	private Sistema sistema;

	@Column(name = "NOME")
	private String nome;
	
	
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
