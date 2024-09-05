package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.Sede;


public class SedeRowMapper implements RowMapper<Sede>{

	@Override
	public Sede mapRow(ResultSet rs, int rowNum) throws SQLException {
		Sede sede = new Sede();
		sede.setCodigo(rs.getLong("sed_codigo"));
		sede.setNombre(rs.getString("sed_nombre"));
		return sede;
	}

}
