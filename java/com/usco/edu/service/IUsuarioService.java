package com.usco.edu.service;

import com.usco.edu.entities.Role;
import com.usco.edu.entities.Usuario;

public interface IUsuarioService {

	public Usuario findByUsername(String username);
	public Role roles(String username);
}
