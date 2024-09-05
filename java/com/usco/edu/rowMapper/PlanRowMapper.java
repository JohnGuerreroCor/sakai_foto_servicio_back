package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.Plan;

public class PlanRowMapper implements RowMapper<Plan>{

	@Override
	public Plan mapRow(ResultSet rs, int rowNum) throws SQLException {
		Plan plan = new Plan();
		plan.setCodigo(rs.getLong("pla_codigo"));
		plan.setCreditosTotal(rs.getInt("pla_total_creditos"));
		plan.setEstado(rs.getInt("pla_estado"));
		plan.setPla_creditos(rs.getInt("pla_creditos"));
		plan.setPla_nombre(rs.getString("pla_nombre"));
		plan.setPlan_actual(rs.getInt("pla_actual"));
		plan.setResolucion(new ResolucionRowMapper().mapRow(rs, rowNum));
		plan.setPrograma(new ProgramaSimplRowMapper().mapRow(rs, rowNum));
		plan.setTipoRegistro(rs.getString("pla_tipo_registro"));
		return plan;
	}

}
