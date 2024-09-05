package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.Asignatura;
import com.usco.edu.rowMapper.AsignaturaRowMapper;

public class AsigSetExtractor implements ResultSetExtractor<List<Asignatura>>{

	@Override
	public List<Asignatura> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<Asignatura> list = new ArrayList<Asignatura>();
		while (rs.next()) {
			list.add(new AsignaturaRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}
		
		return list;
	}

}
