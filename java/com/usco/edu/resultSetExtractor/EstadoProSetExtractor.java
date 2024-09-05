package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.Estado;
import com.usco.edu.rowMapper.EstadoProRowMapper;

public class EstadoProSetExtractor implements ResultSetExtractor<List<Estado>>{

	@Override
	public List<Estado> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<Estado> list = new ArrayList<Estado>();
		while (rs.next()) {
			list.add(new EstadoProRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}
		
		return list;
	}

}
