package br.com.model.acesso;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import br.com.model.BasicEntity;

@Entity
public class Role extends BasicEntity<Long> implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
		
	@Column(name="nome")
	private String nome;

	@Column(name="link")
	private String link;

	@OneToMany(mappedBy = "secao")
	private List<UserRole> permissaoList;
	
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

	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}

	public List<UserRole> getPermissaoList() {
		return permissaoList;
	}
	public void setPermissaoList(List<UserRole> permissaoList) {
		this.permissaoList = permissaoList;
	}
	
}
