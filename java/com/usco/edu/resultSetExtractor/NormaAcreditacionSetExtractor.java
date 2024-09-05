package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.NormaAcreditacion;
import com.usco.edu.rowMapper.NormaAcreditacionRowMapper;

public class NormaAcreditacionSetExtractor implements ResultSetExtractor<List<NormaAcreditacion>> {

	@Override
	public List<NormaAcreditacion> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<NormaAcreditacion> list = new ArrayList<NormaAcreditacion>();
		while (rs.next()) {
			list.add(new NormaAcreditacionRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}
		
		return list;
	}

}
