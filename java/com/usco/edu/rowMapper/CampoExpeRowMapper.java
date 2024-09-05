package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.CampoAmplio;
import com.usco.edu.entities.CampoEspecifico;

public class CampoExpeRowMapper implements RowMapper<CampoEspecifico> {

	@Override
	public CampoEspecifico mapRow(ResultSet rs, int rowNum) throws SQLException {
		CampoAmplio ca = new CampoAmplio();
		ca.setNombre(rs.getString("cam_nombre"));
		ca.setCodigoCine(rs.getString("codigo_cine"));
		ca.setCodigo(rs.getInt("cam_codigo"));

		CampoEspecifico campoEspecifico = new CampoEspecifico();
		campoEspecifico.setCodigo(rs.getInt("cam_esp_codigo"));
		campoEspecifico.setNombre(rs.getString("cam_esp_nombre"));
		campoEspecifico.setCodigoCine(rs.getString("codigo_cine"));
		campoEspecifico.setCampoAmplio(ca);
		return campoEspecifico;
	}

}
