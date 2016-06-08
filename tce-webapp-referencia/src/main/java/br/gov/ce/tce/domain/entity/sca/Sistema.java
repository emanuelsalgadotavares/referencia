package br.gov.ce.tce.domain.entity.sca;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import br.gov.ce.tce.domain.entity.BasicEntity;

@Entity
@Table(name="SISTEMA", schema="SCA")
@NamedQueries({
	@NamedQuery(name = "Sistema.findAll", query = "SELECT s FROM Sistema s ORDER BY s.nome"),
	@NamedQuery(name = "Sistema.findMaxId", query = "SELECT MAX(s.id) FROM Sistema s"),
	@NamedQuery(name = "Sistema.findById", query = "SELECT s FROM Sistema s WHERE s.id = :id"),
	@NamedQuery(name = "Sistema.findBySistema", query = "SELECT s.id FROM Sistema s WHERE s.id = :id"),
	@NamedQuery(name = "Sistema.findByNome", query = "SELECT s FROM Sistema s WHERE UPPER(s.nome) LIKE :nome"),
	@NamedQuery(name = "Sistema.findbyNomeComplete", query = "SELECT s FROM Sistema s WHERE UPPER(s.nome) LIKE :nome"),
	@NamedQuery(name = "Sistema.findBySigla", query = "SELECT s FROM Sistema s WHERE UPPER(s.sigla) LIKE :sigla"),
//	@NamedQuery(name = "Sistema.findbyNome", query = "SELECT s.nome FROM Sistema s WHERE s.nome LIKE :nome OR s.sigla LIKE :nome"),
	@NamedQuery(name = "Sistema.findSiglas", query = "SELECT DISTINCT s.sigla FROM Sistema s")
})
public class Sistema extends BasicEntity<Long> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID")
	private Long id;

	@Column(name="NOME")
	private String nome;

	@Column(name="VERSAOATUAL")
	private String versaoAtual;
	
	@Column(name="SIGLA")
	private String sigla;

	/*@OneToMany(mappedBy="sistema", fetch=FetchType.EAGER) 
	@Fetch(FetchMode.SUBSELECT)
	private List<Grupo> grupoList;*/

	@OneToMany(mappedBy="sistema", fetch=FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	private List<Secao> secaoList;
	
	@OneToMany(mappedBy="sistema", fetch=FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	private List<Grupo> grupoList;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getVersaoAtual() {
		return versaoAtual;
	}

	public void setVersaoAtual(String versaoAtual) {
		this.versaoAtual = versaoAtual;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	/*public List<Grupo> getGrupoList() {
		return grupoList;
	}

	public void setGrupoList(List<Grupo> grupoList) {
		this.grupoList = grupoList;
	}*/

	public List<Secao> getSecaoList() {
		return secaoList;
	}

	public void setSecaoList(List<Secao> secaoList) {
		this.secaoList = secaoList;
	}

	public List<Grupo> getGrupoList() {
		return grupoList;
	}

	public void setGrupoList(List<Grupo> grupoList) {
		this.grupoList = grupoList;
	}

}