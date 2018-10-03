package br.com.entity.acesso;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.BatchSize;

import br.com.model.BasicEntity;

@Entity
@BatchSize(size=1000)
public class GrupoUsuario extends BasicEntity<Long> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	@ManyToOne(fetch=FetchType.LAZY) 
	@JoinColumn(name = "GRUPO")	
	private Grupo grupo;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="USUARIO")	
	private Usuario usuario;

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

	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
