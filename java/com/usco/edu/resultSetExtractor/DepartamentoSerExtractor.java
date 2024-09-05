package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.Departamento;
import com.usco.edu.rowMapper.DepartamentoRowMapper;

public class DepartamentoSerExtractor implements ResultSetExtractor<List<Departamento>> {

	@Override
	public List<Departamento> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<Departamento> list = new ArrayList<Departamento>();
		while (rs.next()) {
			list.add(new DepartamentoRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}
		
		return list;
	}

}
