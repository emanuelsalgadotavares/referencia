package br.com.model.acesso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.model.BasicEntity;

@Entity
public class User extends BasicEntity<Long> implements Serializable, UserDetails {

	private static final long serialVersionUID = -8451679170281063697L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="nome")
	private String nome;

	@Column(name="login", unique = true)
	private String username;

	@Column(name="senha")
	private String password;
	
	@Column(name="ativo")
	private boolean ativo;
	
	@Column(name="senhaexpirada")
	private boolean senhaExpirada;

	@Column(name="datainclusao")
	private Date dataInclusao;
	
	@Column(name="email")
	private String email;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="groupuser", schema="drivingschool", joinColumns = @JoinColumn(name = "iduser"), inverseJoinColumns = @JoinColumn(name = "idgroup"))
	private List<Group> grupoList;

	@Transient
	private Collection<GrantedAuthority> authorities = new ArrayList<>();

	/* METODOS */
	@Transient
    public String getPrimeiroNome() {
		String[] split = nome.split(" ");

		if(split.length > 0)
			return split[0];

    	return nome;
    }

	public Boolean hasAuthority(String authority){
    	for (GrantedAuthority aut : authorities) {
			if(aut.getAuthority().equals(authority))
				return true;
		}
    	return false;
    }
	
	public Boolean anyGranted(String authorities) {
		// pegando a lista de grants passado
		String[] listaGrants = authorities.split(",");
		for(String permissao : listaGrants) {	
			if (hasAuthority(permissao))
				return true;
		}

    	return false;
    }
	
	/* SPRING SECURITY */
	@Override
	public String getUsername() {
    	return username;
    }
    @Override
    public String getPassword() {
    	return password;
    }
	@Override
    public Collection<GrantedAuthority> getAuthorities() {
    	return this.authorities;
    }
    @Override
    public boolean isEnabled() {
    	return ativo;
    }
    @Override
    public boolean isAccountNonExpired() {
    	return !senhaExpirada;
    }
    @Override
    public boolean isAccountNonLocked() {
    	return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
    	return true;
    }
    
    public void setUsername(String username) {
    	this.username = username;
    }
    public void setPassword(String password) {
    	this.password = password;
    }
    public void setAuthorities(Collection<GrantedAuthority> authorities) {
    	this.authorities = authorities;
    }
	public void setAtivo(boolean ativo) {
    	this.ativo = ativo;
    }
    public void setSenhaExpirada(boolean senhaExpirada){
    	this.senhaExpirada = senhaExpirada;
    }
    public boolean isAtivo() {
		return ativo;
	}
	public boolean isSenhaExpirada() {
		return senhaExpirada;
	}
    
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

	public Date getDataInclusao() {
		return dataInclusao;
	}
	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public List<Group> getGrupoList() {
		return grupoList;
	}
	public void setGrupoList(List<Group> grupoList) {
		this.grupoList = grupoList;
	}

}
