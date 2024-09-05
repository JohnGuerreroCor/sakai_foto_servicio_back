package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.Caracter;
import com.usco.edu.rowMapper.CaracterRowMapper;

public class CaracterSetExtractor implements ResultSetExtractor<List<Caracter>>{

	@Override
	public List<Caracter> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<Caracter> list = new ArrayList<Caracter>();
		while (rs.next()) {
			list.add(new CaracterRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}
		
		return list;
	}

}
