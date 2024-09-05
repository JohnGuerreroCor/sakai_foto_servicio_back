package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.Paa;
import com.usco.edu.rowMapper.PaaRowMapper;

public class PaaSetExtractor implements ResultSetExtractor<List<Paa>>{

	@Override
	public List<Paa> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<Paa> list = new ArrayList<Paa>();
		while (rs.next()) {
			list.add(new PaaRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}
		
		return list;
	}

}
