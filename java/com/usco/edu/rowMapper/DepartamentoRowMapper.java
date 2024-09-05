package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.Departamento;

public class DepartamentoRowMapper implements RowMapper<Departamento>{

	@Override
	public Departamento mapRow(ResultSet rs, int rowNum) throws SQLException {
		Departamento departamento = new Departamento();
		departamento.setCodigo(rs.getInt("dep_codigo"));
		departamento.setNombre(rs.getString("dep_nombre"));
		return departamento;
	}

}
