package com.usco.edu.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.usco.edu.dao.IUsuarioDao;
import com.usco.edu.entities.Rol;
import com.usco.edu.entities.Usuario;
import com.usco.edu.service.IUsuarioService;
import com.usco.edu.util.DisabledException;

@Service
public class UsuarioServiceImpl implements UserDetailsService,IUsuarioService{
	
	private Logger logger = LoggerFactory.getLogger(UsuarioServiceImpl.class);
	
	@Autowired
	private IUsuarioDao usuariodao;


	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		if (!usuariodao.validarUsuario(username)){
			logger.error("Error, no exite el usuario '"+username+"' en el sistema!!!");
			throw new DisabledException("No exite el usuario en el sistema.");
		}
		Usuario usuario = usuariodao.buscarUsuario(username);

		System.out.print("USUARIO:::" + usuario);
		//con este List sacamos todos los roles del usuario que se esta autenticando	
		ArrayList<Rol> roles  = new ArrayList<>();
		List<GrantedAuthority> authorities = roles
				.stream()
				.map(role -> new SimpleGrantedAuthority(((Rol) role).getNombre_rol()))
				.peek(authority -> logger.info("Role: " + authority.getAuthority()))
				.collect(Collectors.toList());
			
		return new User(usuario.getUsername(),usuario.getPassword(), usuario.isState(),true,true,true,authorities);
	}
	

	@Override
	@Transactional(readOnly=true)
	public Usuario buscarUsuario(String username) {
		return usuariodao.buscarUsuario(username);
	}
	
	
}
