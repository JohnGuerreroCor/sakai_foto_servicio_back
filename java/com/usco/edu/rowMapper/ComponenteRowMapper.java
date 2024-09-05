package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.Componente;


public class ComponenteRowMapper implements RowMapper<Componente>{

	@Override
	public Componente mapRow(ResultSet rs, int rowNum) throws SQLException {
		Componente componente = new Componente();
		componente.setCodigo(rs.getInt("com_codigo"));
		componente.setNombre(rs.getString("com_nombre"));
		componente.setAcronimo(rs.getString("com_acronimo"));
		return componente;
	}

}
