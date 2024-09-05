package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.Paa;

public class PaaRowMapper implements RowMapper<Paa>{

	@Override
	public Paa mapRow(ResultSet rs, int rowNum) throws SQLException {
		Paa paa = new Paa();
		paa.setCodigo(rs.getInt("paa_codigo"));
		paa.setEstado(rs.getInt("paa_estado"));
		paa.setSemestre(rs.getInt("paa_semestre"));
		paa.setPlan(rs.getInt("pla_codigo"));
		paa.setCreditos(rs.getInt("paa_credito"));
		paa.setAsignatura(new AsignaturaSimRowMapper().mapRow(rs, rowNum));
		paa.setComponente(new ComponenteRowMapper().mapRow(rs, rowNum));
		paa.setMultiAsignatura(rs.getString("paa_multi_asignatura"));
		paa.setProgramable(rs.getString("paa_programable"));
		paa.setNotaMinima(rs.getDouble("paa_nota_minima"));
		paa.setIntensidad(rs.getString("paa_intensidad"));
		paa.setChequeoRequisito(rs.getString("paa_chequeo_requisito"));
		paa.setPlanAcademico(rs.getString("paa_plan_academico"));
		paa.setSemanaxsemestre(rs.getString("paa_semanaxsemestre"));
		paa.setHtrabajoSemestre(rs.getString("paa_h_trabajo_ind_sem"));
		return paa;
	}

}
