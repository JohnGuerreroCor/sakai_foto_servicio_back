package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.CampoAmplio;
import com.usco.edu.rowMapper.CampoAmplRowMapper;

public class CamAmplSetExtractor implements ResultSetExtractor<List<CampoAmplio>>{

	@Override
	public List<CampoAmplio> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<CampoAmplio> list = new ArrayList<CampoAmplio>();
		while (rs.next()) {
			list.add(new CampoAmplRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}
		
		return list;
	}

}
