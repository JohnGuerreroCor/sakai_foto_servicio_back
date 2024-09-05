package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.Resolucion;
import com.usco.edu.rowMapper.ResolucionRowMapper;

public class ResolucionSetExtractor implements ResultSetExtractor<List<Resolucion>> {

	@Override
	public List<Resolucion> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<Resolucion> list = new ArrayList<Resolucion>();
		while (rs.next()) {
			list.add(new ResolucionRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}
		
		return list;
	}

}
