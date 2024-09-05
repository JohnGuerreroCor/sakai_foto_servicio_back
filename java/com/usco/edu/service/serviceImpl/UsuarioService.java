package com.usco.edu.service.serviceImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.usco.edu.dao.ILoginDao;
import com.usco.edu.dao.IUsuarioDao;
import com.usco.edu.entities.Role;
import com.usco.edu.entities.Usuario;
import com.usco.edu.service.IUsuarioService;
import com.usco.edu.util.ConexionBuilder;
import com.usco.edu.util.DisabledException;
import com.usco.edu.util.SegundaClave;

@Service
public class UsuarioService implements UserDetailsService,IUsuarioService{
	
	private Logger logger = LoggerFactory.getLogger(UsuarioService.class);
	
	@Autowired
	private IUsuarioDao usuariodao;
	
	@Autowired
	private ILoginDao logindao;
	
	@Autowired
	private SegundaClave segundaClaveComponent;
	
	@Autowired
	private ConexionBuilder conexionBuilder;


	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes())
				.getRequest();
		
		if (!usuariodao.validarUser(username)){
			logger.error("Error, no exite el usuario '"+username+"' en el sistema!!!");
			throw new DisabledException("No exite el usuario en el sistema.");
		}
		Usuario usuario = usuariodao.findByUsername(username);
		
		System.out.print('///////', usuario);

		//con este List sacamos todos los roles del usuario que se esta autenticando		
		Role rol = new Role(1,"ROLE_"+usuario.getRole());
		ArrayList<Role> roles  = new ArrayList<>();
		roles.add(rol);
		List<GrantedAuthority> authorities = roles
				.stream()
				.map(role -> new SimpleGrantedAuthority(((Role) role).getNombre_rol()))
				.peek(authority -> logger.info("Role: " + authority.getAuthority()))
				.collect(Collectors.toList());
		
		DataSource datasource = conexionBuilder.construir(usuario, usuario.getPassword());
		Connection conexion = null ;
		/*		
		DataSource datasource = conexionBuilder.construir(usuario, usuario.getPassword());
		Connection conexion = null ;
		
		try {
			conexion =datasource.getConnection();
		}catch (Exception e) {
			throw new DisabledException("La segunda clave es incorrecta.");
		}finally {
			cerrarConexion(conexion);
		}
*/
		
			
		
		return new User(usuario.getUsername(),usuario.getPassword(), usuario.isState(),true,true,true,authorities);
	}

	@Override
	@Transactional(readOnly=true)
	public Usuario findByUsername(String username) {
		return usuariodao.findByUsername(username);
	}

	@Override
	@Transactional(readOnly=true)
	public Role roles(String username) {
		return usuariodao.roles(username);
	}
	
	
	private void cerrarConexion(Connection conexion) {
		if (conexion != null) {
			try {
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
