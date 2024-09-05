package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.NormativaTipo;
import com.usco.edu.rowMapper.NormativaTipoRowMapper;

public class NormativaTipoSetExtractor implements ResultSetExtractor<List<NormativaTipo>>{

	@Override
	public List<NormativaTipo> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<NormativaTipo> list = new ArrayList<NormativaTipo>();
		while (rs.next()) {
			list.add(new NormativaTipoRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}
		
		return list;
	}

}
