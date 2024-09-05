package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.Programa;
import com.usco.edu.rowMapper.ProgramaSimplRowMapper;

public class ProgramSimplSetExtractor implements ResultSetExtractor<List<Programa>> {
	@Override
	public List<Programa> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<Programa> list = new ArrayList<Programa>();
		while (rs.next()) {
			list.add(new ProgramaSimplRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}
		
		return list;
	}
}
