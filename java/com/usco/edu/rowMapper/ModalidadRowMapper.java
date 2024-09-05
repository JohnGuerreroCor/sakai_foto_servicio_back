package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.Modalidad;

public class ModalidadRowMapper implements RowMapper<Modalidad> {

	@Override
	public Modalidad mapRow(ResultSet rs, int rowNum) throws SQLException {
		Modalidad modalidad = new Modalidad();
		modalidad.setCodigo(rs.getLong("mod_codigo"));
		modalidad.setNombre(rs.getString("mod_nombre"));
		return modalidad;
	}

}
