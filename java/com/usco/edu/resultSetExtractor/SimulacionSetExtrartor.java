package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.SimulacionHomo;
import com.usco.edu.rowMapper.SimulacionHomoRowMapper;

public class SimulacionSetExtrartor implements ResultSetExtractor<List<SimulacionHomo>> {

	@Override
	public List<SimulacionHomo> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<SimulacionHomo> list = new ArrayList<SimulacionHomo>();
		while (rs.next()) {
			list.add(new SimulacionHomoRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}
		
		return list;
	}

}
