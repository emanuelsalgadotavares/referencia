package br.com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dao.RoleDAO;
import br.com.model.acesso.Role;
import br.com.model.acesso.User;

@Service
public class RoleService {

	@Autowired
	private RoleDAO roleDAO;

	public List<Role> findRoleListByUser(User user) {
		return roleDAO.findRoleListByUser(user);
	}

}
