package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.NormaNivelAcademico;

public class NormaNivelAcademicoRowMapper implements RowMapper<NormaNivelAcademico> {

	@Override
	public NormaNivelAcademico mapRow(ResultSet rs, int rowNum) throws SQLException {

		NormaNivelAcademico nna = new NormaNivelAcademico();
		nna.setCodigo(rs.getLong("nna_codigo"));
		nna.setEstado(rs.getInt("nna_estado"));
		nna.setNivelAcademico(new NivelAcademicoRowMapper().mapRow(rs, rowNum));
		nna.setNombre(rs.getString("nna_nombre"));
		nna.setNorma(new NormaAcreditacionRowMapper().mapRow(rs, rowNum));

		return nna;
	}

}
