package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.Snies_nbc;
import com.usco.edu.rowMapper.Snies_nbcRowMapper;

public class NbcSetExtractor implements ResultSetExtractor<List<Snies_nbc>>{

	@Override
	public List<Snies_nbc> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<Snies_nbc> list = new ArrayList<Snies_nbc>();
		while (rs.next()) {
			list.add(new Snies_nbcRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}
		
		return list;
	}

}
