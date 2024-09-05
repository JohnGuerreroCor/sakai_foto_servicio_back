package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.CampoDetallado;
import com.usco.edu.rowMapper.CampoDetRowMapper;

public class CamDetSetExtractor implements ResultSetExtractor<List<CampoDetallado>>{

	@Override
	public List<CampoDetallado> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<CampoDetallado> list = new ArrayList<CampoDetallado>();
		while (rs.next()) {
			list.add(new CampoDetRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}
		
		return list;
	}

}
