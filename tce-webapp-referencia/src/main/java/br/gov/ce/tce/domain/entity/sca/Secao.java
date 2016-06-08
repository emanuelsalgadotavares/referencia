package br.gov.ce.tce.domain.entity.sca;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import br.gov.ce.tce.domain.entity.BasicEntity;

@Entity
@Table(name="SECAO", schema="SCA")
@NamedQueries({ 
	@NamedQuery(name = "Secao.findAll", query = "SELECT s FROM Secao s ORDER BY s.nome"),
	@NamedQuery(name = "Secao.findById", query = "SELECT s FROM Secao s WHERE s.id = :id"),
	@NamedQuery(name = "Secao.findByIdAndSistemaSAP", query = "SELECT s FROM Secao s WHERE s.id = :id AND s.sistema.id = 2"),
	@NamedQuery(name = "Secao.findByNome", query = "SELECT s FROM Secao s WHERE UPPER(s.nome) LIKE :nome ORDER BY s.id"),
	@NamedQuery(name = "Secao.findByLink", query = "SELECT s FROM Secao s WHERE s.sistema = :sistema AND UPPER(s.nome) LIKE :nome AND s.link LIKE :link"),
	@NamedQuery(name = "Secao.findMaxId", query = "SELECT MAX(s.id) FROM Secao s"),
//	@NamedQuery(name = "Secao.findBySecaoPai", query = "SELECT s FROM Secao s WHERE s.secaoPai = :secaoPai ORDER BY s.id"),
//	@NamedQuery(name = "Secao.findSecaoRaizSistema", query = "SELECT s FROM Secao s WHERE s.secaoPai = :secaoPai AND s.sistema = :sistema ORDER BY s.id"),
	@NamedQuery(name = "Secao.findBySistema", query = "SELECT s FROM Secao s WHERE s.sistema = :sistema"),
	@NamedQuery(name = "Secao.findBySistemaPaginado", query = "SELECT s FROM Secao s JOIN FETCH s.sistema WHERE s.sistema = :sistema AND UPPER (s.nome) LIKE :nome"),
	@NamedQuery(name = "Secao.countByNome", query = "SELECT count(s) FROM Secao s WHERE UPPER( s.nome ) LIKE :nome "),
	@NamedQuery(name = "Secao.countByLink", query = "SELECT count(s) FROM Secao s WHERE s.sistema = :sistema AND UPPER( s.nome ) LIKE :nome AND s.link LIKE :link"),
	@NamedQuery(name = "Secao.countBySistema", query = "SELECT count(s) FROM Secao s WHERE s.sistema = :sistema AND UPPER (s.nome) LIKE :nome"),
	@NamedQuery(name = "Secao.findNomeBySistema", query = "SELECT s FROM Secao s Where s.sistema =:sistema AND UPPER (s.nome) = :nome"),
	@NamedQuery(name = "Secao.searchByNome", query = "SELECT s FROM Secao s JOIN FETCH s.sistema WHERE UPPER(s.nome) LIKE :nome ORDER BY s.nome")
})
		
public class Secao extends BasicEntity<Long> implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY) 
	@JoinColumn(name = "SISTEMA")	
	private Sistema sistema;
		
	@Column(name="NOME")
	private String nome;

	@Column(name="LINK")
	private String link;	
	
	@Column(name="SECAOPAI")
	private Long secaoPai;
	
	@JoinColumn(name="SECAOPAI")  
	@OneToMany(fetch=FetchType.LAZY)
	@OrderBy(value = "ordem asc")
	private List<Secao> secaoFilhoList; 

	@OneToMany(mappedBy = "secao")
	private List<Permissao> permissaoList;
	
	@Column(name="ORDEM")
	private Integer ordem;
	
	@Column(name="MENU")
	private Integer menu;
	
	@Column(name="NIVEL")
	private Integer nivel;
	
	
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
	
	public Long getSecaoPai() {
		return secaoPai;
	}

	public void setSecaoPai(Long secaoPai) {
		this.secaoPai = secaoPai;
	}
	
	public List<Secao> getSecaoFilhoList() {
		return secaoFilhoList;
	}
	
	public void setSecaoFilhoList(List<Secao> secaoFilhoList) {
		this.secaoFilhoList = secaoFilhoList;
	}

	public Integer getOrdem() {
		return ordem;
	}

	public void setOrdem(Integer ordem) {
		this.ordem = ordem;
	}

	public Integer getMenu() {
		return menu;
	}

	public void setMenu(Integer menu) {
		this.menu = menu;
	}

	public Integer getNivel() {
		return nivel;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}

	public Sistema getSistema() {
		return sistema;
	}

	public void setSistema(Sistema sistema) {
		this.sistema = sistema;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public List<Permissao> getPermissaoList() {
		return permissaoList;
	}

	public void setPermissaoList(List<Permissao> permissaoList) {
		this.permissaoList = permissaoList;
	}
	
}
