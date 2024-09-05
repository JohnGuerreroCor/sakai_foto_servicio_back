package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.Jornada;

public class JornadaRowMapper implements RowMapper<Jornada>{

	@Override
	public Jornada mapRow(ResultSet rs, int rowNum) throws SQLException {
		Jornada jornada = new Jornada();
		jornada.setCodigo(rs.getLong("jor_codigo"));
		jornada.setNombre(rs.getString("jor_nombre"));
		return jornada;
	}

}
