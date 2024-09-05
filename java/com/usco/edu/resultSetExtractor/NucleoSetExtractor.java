package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.Nucleo;
import com.usco.edu.rowMapper.NucleoRowMapper;

public class NucleoSetExtractor implements ResultSetExtractor<List<Nucleo>>{

	@Override
	public List<Nucleo> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<Nucleo> list = new ArrayList<Nucleo>();
		while (rs.next()) {
			list.add(new NucleoRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}
		
		return list;
	}

}
