package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.Estado;

public class EstadoProRowMapper implements RowMapper<Estado>{

	@Override
	public Estado mapRow(ResultSet rs, int rowNum) throws SQLException {
		Estado estado = new Estado();
		estado.setCodigo(rs.getInt("pre_codigo"));
		estado.setNombre(rs.getString("pre_nombre"));
		return estado;
	}

}
