package com.usco.edu.dao.daoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.usco.edu.dao.IAdministrativoDao;

@Repository
public class AdministrativoDaoImpl implements IAdministrativoDao{

	
	@Autowired
	@Qualifier("JDBCTemplatePlanesConsulta")
	public JdbcTemplate jdbcTemplate;

	@Override
	public String getTokenInicioSesion(String atributos, String userdb) {
		//DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		//NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);	
		
		String token = null;
		try {
			token = jdbcTemplate.queryForObject("SELECT token.getTokenInicioSesion(?)", new Object[] { atributos }, String.class);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return token;

	}

	@Override
	public String urltokenPeticion( String userdb) {
		
		String url = null;
		try {
			url = jdbcTemplate.queryForObject("SELECT  wep_valor FROM dbo.web_parametro where wep_nombre like '%TOKEN_URL_AUTENTICACION%' " , String.class);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return url;

	}
	

}
