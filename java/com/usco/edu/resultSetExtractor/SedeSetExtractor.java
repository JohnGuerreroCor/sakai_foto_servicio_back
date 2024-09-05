package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.Sede;
import com.usco.edu.rowMapper.SedeRowMapper;

public class SedeSetExtractor implements ResultSetExtractor<List<Sede>>{

	@Override
	public List<Sede> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<Sede> list = new ArrayList<Sede>();
		while (rs.next()) {
			list.add(new SedeRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}
		
		return list;
	}

}
