package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.Programa;

public class ProgramaSimplRowMapper implements RowMapper<Programa>{

	@Override
	public Programa mapRow(ResultSet rs, int rowNum) throws SQLException {
		Programa programa = new Programa();
		programa.setCodigo(rs.getLong("pro_codigo"));
		programa.setEstado(new EstadoProRowMapper().mapRow(rs, rowNum));
		programa.setNombre(rs.getString("pro_titulo_otorgado"));
		programa.setRegistroIcfes(rs.getString("pro_registro_icfes"));
		programa.setRegistroSnies(rs.getString("pro_registro_snies"));
		programa.setCalendario(rs.getString("pro_calendario"));
		programa.setHorario(rs.getString("pro_horario"));
		programa.setFecha_creacion(rs.getString("pro_fecha_creacion"));
		programa.setExtension(rs.getString("pro_extension_snies"));
		programa.setProPropio(rs.getString("pro_propio"));
		programa.setUaa(new UaaSimpleRowMapper().mapRow(rs, rowNum));
		programa.setSede(new SedeRowMapper().mapRow(rs, rowNum));
		programa.setModalidad(new ModalidadRowMapper().mapRow(rs, rowNum));
		programa.setNivelAcademico(new NivelAcademicoRowMapper().mapRow(rs, rowNum));
		return programa;
	}

}
