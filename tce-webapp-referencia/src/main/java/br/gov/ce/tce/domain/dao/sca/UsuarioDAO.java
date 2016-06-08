package br.gov.ce.tce.domain.dao.sca;

import java.util.List;

import br.gov.ce.tce.domain.entity.sca.Usuario;

public interface UsuarioDAO {
	public List<Usuario> findAll();

	public Usuario findById(Long id);

	public Usuario findByUsername(String nome);

	public Usuario findByUsuario(Usuario usuario);

	public void salvar(Usuario usuario);

	public Usuario findByCpf(String cpf);

	public List<Usuario> findByNome(String nome);
	
}