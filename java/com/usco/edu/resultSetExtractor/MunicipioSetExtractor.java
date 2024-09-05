package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.Municipio;
import com.usco.edu.rowMapper.MunicipioRowMapper;

public class MunicipioSetExtractor implements ResultSetExtractor<List<Municipio>> {

	@Override
	public List<Municipio> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<Municipio> list = new ArrayList<Municipio>();
		while (rs.next()) {
			list.add(new MunicipioRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}
		
		return list;
	}

}
