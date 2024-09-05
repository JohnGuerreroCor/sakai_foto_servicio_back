package com.usco.edu.dao;


import com.usco.edu.entities.Role;
import com.usco.edu.entities.Usuario;



public interface IUsuarioDao { //extends CrudRepository<Usuario, Long>{
	/*
	@Query(value = "SELECT * FROM dbo.usuario_planes_academicos_admin_login u WHERE u.us = ?", 
			  nativeQuery = true)
	public Usuario findByUsername(String username);
	
	@Query(value = "SELECT 'ROLE_' +gru_id AS role FROM dbo.usuario_planes_academicos_admin_login u WHERE u.us = ?", 
			  nativeQuery = true)
	public Role roles(String username);
	*/
	
	public Usuario findByUsername(String username);
	public Role roles(String username);
	public boolean validarUser(String username);


}
