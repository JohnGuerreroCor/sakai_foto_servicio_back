package com.usco.edu.dao.daoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.usco.edu.dao.IFotoDao;

@Repository
public class FotoDaoImpl implements IFotoDao {
	
	@Autowired
	@Qualifier("JDBCTemplateConsulta")
	public JdbcTemplate jdbcTemplate;

	@Override
	public String obtenerTokenFoto(String atributos) {
		String sql = "SELECT dbo.getTokenDocumento(?)";
		return jdbcTemplate.queryForObject(sql, new Object[] { atributos }, String.class);
	}
	
	@Override
	public String obtenerTokenFotoVisualizar(String atributos) {
		String sql = "SELECT dbo.getTokenDocumento(?) as token";
		return jdbcTemplate.queryForObject(sql, new Object[] { atributos }, String.class);
	}
	
	@Override
	public String obtenerPercodigoEstudiante(String codigo) {
		String sql = "select e.per_codigo from estudiante e where e.est_codigo = ? and e.est_registro_egresado = 0;";
	    try {
	        return jdbcTemplate.queryForObject(sql, new Object[] { codigo }, String.class);
	    } catch (EmptyResultDataAccessException e) {
	        // Retorna "0" si no se encuentra un resultado
	        return "0";
	    }
	}
	
	@Override
	public String obtenerPercodigoDocente(String codigo) {
		String sql = "select p.per_codigo from persona p where p.per_identificacion = ?";
		try {
	        return jdbcTemplate.queryForObject(sql, new Object[] { codigo }, String.class);
	    } catch (EmptyResultDataAccessException e) {
	        // Retorna "0" si no se encuentra un resultado
	        return "0";
	    }
	}

}
