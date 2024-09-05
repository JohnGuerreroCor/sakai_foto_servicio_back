package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.NivelAcademico;

public class NivelAcademicoRowMapper implements RowMapper<NivelAcademico> {

	@Override
	public NivelAcademico mapRow(ResultSet rs, int rowNum) throws SQLException {
		NivelAcademico nivelAcademico = new NivelAcademico();
		nivelAcademico.setCodigo(rs.getLong("nia_codigo"));
		nivelAcademico.setNombre(rs.getString("nia_nombre"));
		nivelAcademico.setEstado(rs.getString("nia_estado"));
		return nivelAcademico;
	}

}
