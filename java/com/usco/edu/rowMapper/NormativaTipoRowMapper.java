package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.NormativaTipo;

public class NormativaTipoRowMapper implements RowMapper<NormativaTipo>{

	@Override
	public NormativaTipo mapRow(ResultSet rs, int rowNum) throws SQLException {
			NormativaTipo normaT = new NormativaTipo();
			normaT.setCodigo(rs.getLong("tin_codigo"));
			normaT.setNombre(rs.getString("tin_nombre"));
			normaT.setEstado(rs.getInt("tin_estado"));
		return normaT;
	}

}
