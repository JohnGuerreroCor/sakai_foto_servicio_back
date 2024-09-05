package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.Asignatura;

public class AsignaturaRowMapper implements RowMapper<Asignatura>{

	@Override
	public Asignatura mapRow(ResultSet rs, int rowNum) throws SQLException {
		Asignatura asignatura = new Asignatura();
		asignatura.setCodigo(rs.getInt("asi_codigo"));
		asignatura.setCreditos(rs.getInt("asi_creditos"));
		asignatura.setCreditos_teoricos(rs.getInt("asi_creditos_teoria"));
		asignatura.setCreditos_practicos(rs.getInt("asi_creditos_practica"));
		asignatura.setCaracter(new CaracterRowMapper().mapRow(rs, rowNum));
		asignatura.setEstado(rs.getInt("asi_estado"));
		asignatura.setExtramuro(rs.getString("asi_extramuro"));
		asignatura.setNombre(rs.getString("asi_nombre"));
		asignatura.setNombreCorto(rs.getString("asi_nombre_corto"));
		asignatura.setPublicar(rs.getString("asi_publicar"));
		asignatura.setTrabajo_independiente(rs.getString("asi_trabajo_independiente"));
		asignatura.setTrabajo_presencial(rs.getString("asi_trabajo_presencial"));
		asignatura.setNucleo(new NucleoRowMapper().mapRow(rs, rowNum));
		asignatura.setUaa(new UaaSimpleRowMapper().mapRow(rs, rowNum));
		asignatura.setNbc(new Snies_nbcRowMapper().mapRow(rs, rowNum));
		return asignatura;
	}

}
