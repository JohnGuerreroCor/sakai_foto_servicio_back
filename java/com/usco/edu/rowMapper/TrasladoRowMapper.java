package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.Traslado;

public class TrasladoRowMapper  implements RowMapper<Traslado>{

	@Override
	public Traslado mapRow(ResultSet rs, int rowNum) throws SQLException {
		Traslado traslado = new Traslado();
		traslado.setAsignaturanew(rs.getString("nom_new"));
		traslado.setAsignaturaold(rs.getString("nom_old"));
		traslado.setAplica(rs.getString("aplica"));
		traslado.setCodigoPaanew(rs.getString("paa_new"));
		traslado.setCodigoPaaold(rs.getString("paa_old"));
		traslado.setHot_nombre(rs.getString("hot_nombre"));
		traslado.setPlannew(rs.getString("p_new"));
		traslado.setPlanold(rs.getString("p_old"));
		traslado.setSemestreasinew(rs.getString("s_new"));
		traslado.setSemestreasiold(rs.getString("s_old"));
		return traslado;
	}

}
