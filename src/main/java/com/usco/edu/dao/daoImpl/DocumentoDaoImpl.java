package com.usco.edu.dao.daoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.usco.edu.dao.IDocumentoDao;

@Repository
public class DocumentoDaoImpl implements IDocumentoDao {

	@Autowired
	@Qualifier("JDBCTemplateConsulta")
	public JdbcTemplate jdbcTemplate;

	@Override
	public String getKeyDocumento(String atributos, String user) {
		String sql = "SELECT dbo.getTokenDocumento(?)";
		return jdbcTemplate.queryForObject(sql, new Object[] { atributos }, String.class);
	}
	@Override
	public String getKeyDocumentomirar(String atributos, String user) {
		String sql = "SELECT dbo.getTokenDocumento(?) as token";
		return jdbcTemplate.queryForObject(sql, new Object[] { atributos }, String.class);
	}
}
