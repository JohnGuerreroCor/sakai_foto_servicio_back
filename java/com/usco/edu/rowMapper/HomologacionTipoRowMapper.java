package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.HomologacionTipo;

public class HomologacionTipoRowMapper implements RowMapper<HomologacionTipo>{

	@Override
	public HomologacionTipo mapRow(ResultSet rs, int rowNum) throws SQLException {
		HomologacionTipo homoT = new HomologacionTipo();
		homoT.setCodigo(rs.getInt("hot_codigo"));
		homoT.setNombre(rs.getString("hot_nombre"));
		return homoT;
	}

}
