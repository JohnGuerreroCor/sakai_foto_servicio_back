package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.CampoAmplio;
import com.usco.edu.entities.CampoDetallado;
import com.usco.edu.entities.CampoEspecifico;

public class CampoDetRowMapper implements RowMapper<CampoDetallado> {

	@Override
	public CampoDetallado mapRow(ResultSet rs, int rowNum) throws SQLException {

		CampoAmplio ca = new CampoAmplio();
		ca.setNombre(rs.getString("cam_nombre"));
		ca.setCodigoCine(rs.getString("codigo_cine"));
		ca.setCodigo(rs.getInt("cam_codigo"));

		CampoEspecifico campoEspecifico = new CampoEspecifico();
		campoEspecifico.setCodigo(rs.getInt("cam_esp_codigo"));
		campoEspecifico.setNombre(rs.getString("cam_esp_nombre"));
		campoEspecifico.setCodigoCine(rs.getString("codigo_cine"));
		campoEspecifico.setCampoAmplio(ca);

		CampoDetallado campDet = new CampoDetallado();
		campDet.setCodigo(rs.getInt("cam_det_codigo"));
		campDet.setNombre(rs.getString("cam_det_nombre"));
		campDet.setEstado(rs.getInt("cam_det_estado"));
		campDet.setCodigoCine(rs.getString("codigo_cine"));

		campDet.setCampoEspecifico(campoEspecifico);
		return campDet;
	}

}
