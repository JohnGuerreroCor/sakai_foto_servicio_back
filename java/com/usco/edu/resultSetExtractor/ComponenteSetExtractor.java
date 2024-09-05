package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.Componente;
import com.usco.edu.rowMapper.ComponenteRowMapper;

public class ComponenteSetExtractor implements ResultSetExtractor<List<Componente>>{

	@Override
	public List<Componente> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<Componente> list = new ArrayList<Componente>();
		while (rs.next()) {
			list.add(new ComponenteRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}
		
		return list;
	}

}
