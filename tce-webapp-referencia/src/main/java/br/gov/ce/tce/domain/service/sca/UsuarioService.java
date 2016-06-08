package br.gov.ce.tce.domain.service.sca;

import java.util.List;

import br.gov.ce.tce.domain.entity.sca.Usuario;

public interface UsuarioService {

	public List<Usuario> findLista();

	public void salvar(Usuario usuario);

	public Usuario findByUsername(String nome);

	public List<Usuario> findByNome(String nome);

	public Usuario findByUsuario(Usuario usuario);

	public Usuario findById(Long idUsuario);
}
