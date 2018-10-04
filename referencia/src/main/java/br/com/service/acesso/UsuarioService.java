package br.com.service.acesso;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dao.acesso.UsuarioDAO;
import br.com.model.acesso.User;

@Service("usuarioService")
public class UsuarioService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private UsuarioDAO dao;
	
	public List<User> findLista() {
		return dao.findAll();
	}
	
	public User findByUsername(String nome) {
		return dao.findByUsername(nome);
	}

	public List<User> findByNome(String nome) {
		return dao.findByNome(nome);
	}

	public User findByUsuario(User usuario) {
		
		if(usuario.getId() == null || usuario.getId() == 0) {
			throw new IllegalArgumentException("O usuário nao foi selecionado.");
		}
		return dao.findByUsuario(usuario);
	}
	
	public void verificaUsuarioExistenteUsername(User usuario) {
		User u = findByUsername(usuario.getUsername());
		if(u != null) {
			throw new IllegalArgumentException("Já existe um usuário cadastrado com esse Login.");
		}
	}

	public User findById(Long idUsuario) {
		return dao.findById(idUsuario);
	}
}