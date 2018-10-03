package br.com.service.acesso;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dao.acesso.UsuarioDAO;
import br.com.entity.acesso.Usuario;

@Service("usuarioService")
public class UsuarioService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private UsuarioDAO dao;
	
	public List<Usuario> findLista() {
		return dao.findAll();
	}
	
	public Usuario findByUsername(String nome) {
		return dao.findByUsername(nome);
	}

	public List<Usuario> findByNome(String nome) {
		return dao.findByNome(nome);
	}

	public Usuario findByUsuario(Usuario usuario) {
		
		if(usuario.getId() == null || usuario.getId() == 0) {
			throw new IllegalArgumentException("O usu�rio nao foi selecionado.");
		}
		return dao.findByUsuario(usuario);
	}
	
	public void verificaAlteracao(Usuario usuario) {
		//se é um novo usuário
		if(usuario.getId() == null) {
			verificaUsuarioExistenteCpf(usuario);
			verificaUsuarioExistenteUsername(usuario);
			usuario.setDataInclusao(new Date());
			usuario.setSenhaExpirada(false);
		}
		//se é apenas uma alteração
		else {
			Usuario user = findByUsuario(usuario);
			
			usuario.setDataAlteracao(new Date());
			
			if(!user.getCpf().equals(usuario.getCpf())) {
				verificaUsuarioExistenteCpf(usuario);
			}
			if( !user.getUsername().equals(usuario.getUsername())) {
				verificaUsuarioExistenteUsername(usuario);
			}
		}
	}
	
	public void verificaUsuarioExistenteCpf(Usuario usuario) {
		Usuario u = dao.findByCpf(usuario.getCpf());
		if(u != null) {
			throw new IllegalArgumentException("Já existe um usuário cadastrado com esse Cpf.");
		}
	}
	
	public void verificaUsuarioExistenteUsername(Usuario usuario) {
		Usuario u = findByUsername(usuario.getUsername());
		if(u != null) {
			throw new IllegalArgumentException("Já existe um usuário cadastrado com esse Login.");
		}
	}

	public Usuario findById(Long idUsuario) {
		return dao.findById(idUsuario);
	}
}