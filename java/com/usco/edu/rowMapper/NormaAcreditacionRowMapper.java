package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.NormaAcreditacion;

public class NormaAcreditacionRowMapper implements RowMapper<NormaAcreditacion> {

	@Override
	public NormaAcreditacion mapRow(ResultSet rs, int rowNum) throws SQLException {
		NormaAcreditacion na = new NormaAcreditacion();
		na.setCodigo(rs.getInt("noa_codigo"));
		na.setFin(rs.getDate("noa_fecha_fin"));
		na.setInicio(rs.getDate("noa_fecha_inicio"));
		return na;
	}

}
