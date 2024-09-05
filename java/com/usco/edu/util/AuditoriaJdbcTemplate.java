package com.usco.edu.util;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class AuditoriaJdbcTemplate {
	
	@Autowired
	private SegundaClave segundaClave;
	
	@Autowired
	private ConexionBuilder conexionBuilder;
	
	
	public NamedParameterJdbcTemplate construirTemplate(String usuario ) {
		DataSource dataSource = this.construirDataSourceDeUsuario(usuario);
		return new NamedParameterJdbcTemplate(dataSource);
		
	}
	
	
	public NamedParameterJdbcTemplate construirTemplatenew(DataSource dataSource ) {
		//DataSource dataSource = this.construirDataSourceDeUsuario(usuario);
		return new NamedParameterJdbcTemplate(dataSource);
		
	}
	
	public DataSource construirDataSourceDeUsuario(String usuario) {
		return conexionBuilder.construir(segundaClave.getUser(usuario), segundaClave.getClave(usuario));
		
	}
	
	
}
