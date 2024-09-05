package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.Convenio;

public class ConvenioRowMapper implements RowMapper<Convenio> {

	@Override
	public Convenio mapRow(ResultSet rs, int rowNum) throws SQLException {
		Convenio convenio = new Convenio();
		convenio.setCodigo(rs.getInt("con_codigo"));
		convenio.setNombre(rs.getString("con_descripcion"));
		convenio.setEstado(rs.getInt("con_estado"));
		return convenio;
	}

}
