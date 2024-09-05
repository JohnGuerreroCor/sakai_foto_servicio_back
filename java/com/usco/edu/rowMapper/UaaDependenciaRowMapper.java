package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.Uaa;


public class UaaDependenciaRowMapper implements RowMapper<Uaa> {

	@Override
	public Uaa mapRow(ResultSet rs, int rowNum) throws SQLException {
		Uaa uaa = new Uaa();
		uaa.setCodigo(rs.getLong("uaa_dependencia"));
		uaa.setEstado(rs.getInt("uaa_estado"));
		uaa.setNombre(rs.getString("uaa_nombre"));
		uaa.setNombreCorto(rs.getString("uaa_nombre_corto"));
		uaa.setUaaTipo(new UaaTipoRowMapper().mapRow(rs, rowNum));
		return uaa;
	}

}
