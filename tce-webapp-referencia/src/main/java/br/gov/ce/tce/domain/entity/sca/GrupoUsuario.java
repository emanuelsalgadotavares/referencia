package br.gov.ce.tce.domain.entity.sca;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;

import br.gov.ce.tce.domain.entity.BasicEntity;

@Entity
@Table(name="GRUPOUSUARIO", schema="SCA")
@BatchSize(size=1000)
@NamedQueries({
	@NamedQuery(name = "GrupoUsuario.findMaxId", query = "SELECT MAX(gu.id) FROM GrupoUsuario gu"),
	@NamedQuery(name = "GrupoUsuario.countGrupoUsuario", query = "SELECT count(gu.id) FROM GrupoUsuario gu WHERE gu.grupo = :grupo")
})
public class GrupoUsuario extends BasicEntity<Long> implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	@ManyToOne(fetch=FetchType.LAZY) 
	@JoinColumn(name = "GRUPO")	
	private Grupo grupo;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="USUARIO")	
	private Usuario usuario;

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
