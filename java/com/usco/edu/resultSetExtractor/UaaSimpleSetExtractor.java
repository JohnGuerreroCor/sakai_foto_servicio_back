package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.Uaa;
import com.usco.edu.rowMapper.UaaSimpleRowMapper;

public class UaaSimpleSetExtractor implements ResultSetExtractor<List<Uaa>>{

	@Override
	public List<Uaa> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<Uaa> list = new ArrayList<Uaa>();
		while (rs.next()) {
			list.add(new UaaSimpleRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}
		
		return list;
	}

}
