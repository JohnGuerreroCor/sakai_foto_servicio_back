package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.SimulacionHomo;

public class SimulacionHomoRowMapper implements RowMapper<SimulacionHomo>{

	@Override
	public SimulacionHomo mapRow(ResultSet rs, int rowNum) throws SQLException {
	
		SimulacionHomo simulacion = new SimulacionHomo();
		
		
		ResultSetMetaData rsmd = rs.getMetaData();
			
			if(rsmd.getColumnCount() <= 7) {
				simulacion.setCodigoPaanew(rs.getLong("paa_codigo_nuevo"));
				simulacion.setCodigoPaaold(rs.getLong("paa_codigo_viejo"));
				simulacion.setAsignaturanew(rs.getString("nom_new"));
				simulacion.setAsignaturaold(rs.getString("nom_old"));
				simulacion.setHot_codigo(rs.getInt("hot_codigo"));
				simulacion.setHot_nombre(rs.getString("hot_nombre"));
				simulacion.setCodigo(rs.getLong("hom_codigo"));
			}else {
				simulacion.setCodigoPaanew(rs.getLong("paa_codigo_nuevo"));
				simulacion.setCodigoPaaold(rs.getLong("paa_codigo_viejo"));
				simulacion.setAsignaturanew(rs.getString("nom_new"));
				simulacion.setAsignaturaold(rs.getString("nom_old"));
				simulacion.setNota(rs.getDouble("mac_definitiva"));
				simulacion.setHot_codigo(rs.getInt("hot_codigo"));
				simulacion.setCurso(rs.getString("cur_codigo"));
				simulacion.setMatricula(rs.getLong("mat_codigo"));
				simulacion.setSubgrupo(rs.getString("mac_subgrupo"));
				simulacion.setUaanew(rs.getString("uaa_new"));
			}

		return simulacion;
	}

}
