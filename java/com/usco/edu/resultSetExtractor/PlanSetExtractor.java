package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.Plan;
import com.usco.edu.rowMapper.PlanRowMapper;

public class PlanSetExtractor implements ResultSetExtractor<List<Plan>> {

	@Override
	public List<Plan> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<Plan> list = new ArrayList<Plan>();
		while (rs.next()) {
			list.add(new PlanRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}
		
		return list;
	}

}
