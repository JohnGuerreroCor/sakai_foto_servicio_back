package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.Estudiante;

public class EstudianteRowMapper implements RowMapper<Estudiante>{

	@Override
	public Estudiante mapRow(ResultSet rs, int rowNum) throws SQLException {
		Estudiante est = new Estudiante();
		est.setCodigo(rs.getString("est_codigo"));
		est.setNombre(rs.getString("nombre"));
		est.setPlan(rs.getString("pla_nombre"));
		est.setPlanCodigo(rs.getInt("pla_codigo"));
		est.setUaa_codigo(rs.getInt("pro_uaa_codigo"));
		est.setUaa_nombre(rs.getString("uaa_nombre_corto"));
		return est;
	}

}
