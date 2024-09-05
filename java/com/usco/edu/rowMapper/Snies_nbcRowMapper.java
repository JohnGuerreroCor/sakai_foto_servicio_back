package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.Snies_nbc;

public class Snies_nbcRowMapper implements RowMapper<Snies_nbc> {

	@Override
	public Snies_nbc mapRow(ResultSet rs, int rowNum) throws SQLException {
		Snies_nbc nbc = new Snies_nbc();
		nbc.setCodigo(rs.getInt("nbc_codigo"));
		nbc.setNombre(rs.getString("nbc_nombre"));
		nbc.setSnies_area(rs.getInt("sar_codigo"));
		return nbc;
	}

}
