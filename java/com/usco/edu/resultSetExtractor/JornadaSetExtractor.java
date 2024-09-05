package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.Jornada;
import com.usco.edu.rowMapper.JornadaRowMapper;

public class JornadaSetExtractor  implements ResultSetExtractor<List<Jornada>>{

	@Override
	public List<Jornada> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<Jornada> list = new ArrayList<Jornada>();
		while (rs.next()) {
			list.add(new JornadaRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}
		
		return list;
	}

}
