package br.gov.ce.tce.domain.entity.sca;

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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.gov.ce.tce.domain.entity.BasicEntity;

@Entity
@Table(name="USUARIO", schema="SCA")
@NamedQueries({
  @NamedQuery(name = "Usuario.findById", query = "SELECT u FROM Usuario u WHERE u.id = :id"),
  @NamedQuery(name = "Usuario.findByNome", query = "SELECT usu FROM Usuario usu WHERE UPPER( usu.nome ) LIKE :nome ORDER BY usu.nome"),
  @NamedQuery(name = "Usuario.findByUsername", query = "SELECT usu FROM Usuario usu WHERE UPPER(usu.username) = UPPER(:username)"),
  @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u ORDER BY u.id")
})
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
	
	@Column(name="TIPO")
	private Integer tipo;
	
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

	@Column(name="OBSERVACAO")
	private String observacao;
	
	@Column(name="IDSTATUSCERTIFICADO")
	private boolean idStatusCertificado;
	
	@Column(name="DTVALIDADECERTIFICADO")
	private Date dtValidadeCertificado;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="GRUPOUSUARIO", schema="SCA", joinColumns = @JoinColumn(name = "USUARIO"), inverseJoinColumns = @JoinColumn(name = "GRUPO"))
	private List<Grupo> grupoList;

	
	@Transient
	private Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

    @Transient
    public Collection<GrantedAuthority> getAuthorities() {
    	return this.authorities;
    }
    
    @Transient
    public boolean isEnabled() {
    	return ativo;
    }

    @Transient
    public boolean isAccountNonExpired() {
    	return !senhaExpirada;
    }

    @Transient
    public boolean isAccountNonLocked() {
    	return true;
    }

    @Transient
    public boolean isCredentialsNonExpired() {
    	return true;
    }

    public Long getId() {
    	return id;
    }

    public void setId(Long id) {
    	this.id = id;
    }
    
    public String getNome() {
		return nome;
	}

    @Transient
    public String getPrimeiroNome() {
		String[] split = nome.split(" ");
		
		if(split.length > 0){
			return split[0];
		}
    	return nome;
    }

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsername() {
    	return username;
    }

    public void setUsername(String username) {
    	this.username = username;
    }

    public String getPassword() {
    	return password;
    }

    public void setPassword(String password) {
    	this.password = password;
    }
    
    public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setAtivo(boolean ativo) {
    	this.ativo = ativo;
    }

    public void setSenhaExpirada(boolean senhaExpirada){
    	this.senhaExpirada = senhaExpirada;
    }

    public void setAuthorities(Collection<GrantedAuthority> authorities) {
    	this.authorities = authorities;
    }
    
    public Boolean hasAuthority(String authority){
    	for (GrantedAuthority aut : authorities) {
			if(aut.getAuthority().equals(authority)){
				return true;
			}
		}
    	return false;
    }
    
    public Boolean anyGranted(String authorities) {
		// pegando a lista de grants passado
		String[] listaGrants = authorities.split(",");
		for (String permissao : listaGrants) {	
			if (hasAuthority(permissao)) {
				return true;
			}
		}
    	return false;
    }

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
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

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public boolean isIdStatusCertificado() {
		return idStatusCertificado;
	}

	public void setIdStatusCertificado(boolean idStatusCertificado) {
		this.idStatusCertificado = idStatusCertificado;
	}

	public Date getDtValidadeCertificado() {
		return dtValidadeCertificado;
	}

	public void setDtValidadeCertificado(Date dtValidadeCertificado) {
		this.dtValidadeCertificado = dtValidadeCertificado;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public boolean isSenhaExpirada() {
		return senhaExpirada;
	}

	public List<Grupo> getGrupoList() {
		return grupoList;
	}

	public void setGrupoList(List<Grupo> grupoList) {
		this.grupoList = grupoList;
	}

}
