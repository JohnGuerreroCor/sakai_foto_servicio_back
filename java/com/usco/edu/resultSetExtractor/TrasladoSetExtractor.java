package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.Traslado;
import com.usco.edu.rowMapper.TrasladoRowMapper;

public class TrasladoSetExtractor implements ResultSetExtractor<List<Traslado>> {

	@Override
	public List<Traslado> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<Traslado> list = new ArrayList<Traslado>();
		while (rs.next()) {
			list.add(new TrasladoRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}
		
		return list;
	}

}
