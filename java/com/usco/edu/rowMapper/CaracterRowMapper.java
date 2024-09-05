package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.Caracter;

public class CaracterRowMapper implements RowMapper<Caracter>{

	@Override
	public Caracter mapRow(ResultSet rs, int rowNum) throws SQLException {
		Caracter caracter = new Caracter();
		caracter.setCodigo(rs.getString("car_codigo"));
		caracter.setNombre(rs.getString("car_nombre"));
		return caracter;
	}

}
