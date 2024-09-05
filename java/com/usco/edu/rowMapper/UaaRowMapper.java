package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.Uaa;

public class UaaRowMapper implements RowMapper<Uaa>{

	@Override
	public Uaa mapRow(ResultSet rs, int rowNum) throws SQLException {
		Uaa uaa = new Uaa();
		uaa.setCodigo(rs.getLong("uaa_codigo"));
		uaa.setEstado(rs.getInt("uaa_estado"));
		uaa.setNombre(rs.getString("uaa_nombre"));
		uaa.setNombreCorto(rs.getString("uaa_nombre_corto"));
		uaa.setEmail(rs.getString("uaa_email"));
		uaa.setDireccion(rs.getString("uaa_direccion"));
		uaa.setJefe(rs.getString("uaa_jefe"));
		uaa.setPagina(rs.getString("uaa_pagina"));
		uaa.setTelefono(rs.getString("uaa_telefono"));
		uaa.setAcronimo(rs.getString("uaa_acronimo"));
		uaa.setCentro_costos(rs.getString("uaa_centro_costos"));
		uaa.setUaa_dependencia(rs.getInt("uaa_dependencia"));
		uaa.setNombreImpresion(rs.getString("uaa_nombre_impresion"));
		uaa.setUaaTipo(new UaaTipoRowMapper().mapRow(rs, rowNum));
		uaa.setSede(new SedeRowMapper().mapRow(rs, rowNum));
		return uaa;
	}

}
