package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.CampoEspecifico;
import com.usco.edu.rowMapper.CampoExpeRowMapper;

public class CamExpSetExtractor implements ResultSetExtractor<List<CampoEspecifico>>{

	@Override
	public List<CampoEspecifico> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<CampoEspecifico> list = new ArrayList<CampoEspecifico>();
		while (rs.next()) {
			list.add(new CampoExpeRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}
		
		return list;
	}

}
