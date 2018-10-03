package br.com.entity.acesso;

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
public class Usuario extends BasicEntity<Long> implements Serializable, UserDetails {

	private static final long serialVersionUID = -8451679170281063697L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="NOME")
	private String nome;

	@Column(name="LOGIN", unique = true)
	private String username;

	@Column(name="SENHA")
	private String password;
	
	@Column(name="CPF")
	private String cpf;

	@Column(name="ATIVO")
	private boolean ativo;
	
	@Column(name="SENHAEXPIRADA")
	private boolean senhaExpirada;

	@Column(name="DATAALTERACAO")
	private Date dataAlteracao;
	
	@Column(name="DATAINCLUSAO")
	private Date dataInclusao;
	
	@Column(name="EMAIL")
	private String email;

	@Column(name="DTVALIDADECERTIFICADO")
	private Date dtValidadeCertificado;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="GRUPOUSUARIO", schema="SCA", joinColumns = @JoinColumn(name = "USUARIO"), inverseJoinColumns = @JoinColumn(name = "GRUPO"))
	private List<Grupo> grupoList;

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

    public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public Date getDataAlteracao() {
		return dataAlteracao;
	}
	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
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

	public Date getDtValidadeCertificado() {
		return dtValidadeCertificado;
	}
	public void setDtValidadeCertificado(Date dtValidadeCertificado) {
		this.dtValidadeCertificado = dtValidadeCertificado;
	}

	public List<Grupo> getGrupoList() {
		return grupoList;
	}
	public void setGrupoList(List<Grupo> grupoList) {
		this.grupoList = grupoList;
	}

}
