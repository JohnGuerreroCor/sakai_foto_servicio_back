package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.CampoAmplio;

public class CampoAmplRowMapper implements RowMapper<CampoAmplio>{

	@Override
	public CampoAmplio mapRow(ResultSet rs, int rowNum) throws SQLException {
		CampoAmplio campoAmplio = new CampoAmplio();
		campoAmplio.setCodigo(rs.getInt("cam_codigo"));
		campoAmplio.setNombre(rs.getString("cam_nombre"));
		campoAmplio.setEstado(rs.getInt("cam_estado"));
		campoAmplio.setCodigoCine(rs.getString("codigo_cine"));
		return campoAmplio;
	}

}
