package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.Convenio;
import com.usco.edu.rowMapper.ConvenioRowMapper;

public class ConvenioSetExtractor  implements ResultSetExtractor<List<Convenio>>{

	@Override
	public List<Convenio> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<Convenio> list = new ArrayList<Convenio>();
		while (rs.next()) {
			list.add(new ConvenioRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}
		
		return list;
	}

}
