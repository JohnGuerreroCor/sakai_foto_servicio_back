package com.usco.edu.dao;

import com.usco.edu.entities.Usuario;

public interface IUsuarioDao { 
	
	public Usuario buscarUsuario(String username);
	
	public boolean validarUsuario(String username);

}
