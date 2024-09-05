package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.Nucleo;

public class NucleoRowMapper implements RowMapper<Nucleo>{

	@Override
	public Nucleo mapRow(ResultSet rs, int rowNum) throws SQLException {
		Nucleo nucleo = new Nucleo();
		nucleo.setCodigo(rs.getLong("nuc_codigo"));
		nucleo.setNombre(rs.getString("nuc_nombre"));
		nucleo.setAcronimo(rs.getString("nuc_acronimo"));
		nucleo.setEstado(rs.getInt("nuc_estado"));
		return nucleo;
	}

}
