package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.Resolucion;


public class ResolucionRowMapper implements RowMapper<Resolucion>{

	@Override
	public Resolucion mapRow(ResultSet rs, int rowNum) throws SQLException {
		Resolucion res = new  Resolucion();
		res.setCodigo(rs.getLong("res_codigo"));
		res.setDescripcion(rs.getString("res_descripcion"));;
		res.setNumero(rs.getString("res_numero"));
		res.setEstado(rs.getString("res_estado"));
		res.setFecha(rs.getTimestamp("res_fecha"));
		res.setDependencia(rs.getInt("res_dependencia"));
		res.setNormativaTipo(new NormativaTipoRowMapper().mapRow(rs, rowNum));
		return res;
	}

}
