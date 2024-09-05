package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.UaaTipo;

public class UaaTipoRowMapper implements RowMapper<UaaTipo>{

	@Override
	public UaaTipo mapRow(ResultSet rs, int rowNum) throws SQLException {
		UaaTipo uaaTipo = new UaaTipo();
		uaaTipo.setCodigo(rs.getLong("uat_codigo"));
		//uaaTipo.setEstado(rs.getInt("uat_estado"));
		uaaTipo.setNombre(rs.getString("uat_nombre"));
		return uaaTipo;
	}

}
