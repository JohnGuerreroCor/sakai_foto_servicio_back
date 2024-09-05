package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.Modalidad;
import com.usco.edu.rowMapper.ModalidadRowMapper;

public class ModalidadSetExtractor implements ResultSetExtractor<List<Modalidad>>{

	@Override
	public List<Modalidad> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<Modalidad> list = new ArrayList<Modalidad>();
		while (rs.next()) {
			list.add(new ModalidadRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}
		
		return list;
	}

}
