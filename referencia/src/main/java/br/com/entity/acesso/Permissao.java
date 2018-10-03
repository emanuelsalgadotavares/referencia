package br.com.entity.acesso;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import br.com.model.BasicEntity;

@Entity
public class Permissao extends BasicEntity<Long> implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
		
	@Column(name="NOME")
	private String nome;

	@Column(name="LINK")
	private String link;	
	
	@Column(name="SECAOPAI")
	private Long secaoPai;
	
	@JoinColumn(name="SECAOPAI")  
	@OneToMany(fetch=FetchType.LAZY)
	@OrderBy(value = "ordem asc")
	private List<Permissao> secaoFilhoList; 

	@OneToMany(mappedBy = "secao")
	private List<UsuarioPermissao> permissaoList;
	
	@Column(name="ORDEM")
	private Integer ordem;
	
	@Column(name="MENU")
	private Integer menu;
	
	@Column(name="NIVEL")
	private Integer nivel;
	
	/* GET & SET */
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
	
	public List<Permissao> getSecaoFilhoList() {
		return secaoFilhoList;
	}
	public void setSecaoFilhoList(List<Permissao> secaoFilhoList) {
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

	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}

	public List<UsuarioPermissao> getPermissaoList() {
		return permissaoList;
	}
	public void setPermissaoList(List<UsuarioPermissao> permissaoList) {
		this.permissaoList = permissaoList;
	}
	
}
