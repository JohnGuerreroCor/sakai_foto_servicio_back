package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.UaaTipo;
import com.usco.edu.rowMapper.UaaTipoRowMapper;

public class UaaTipoSetExtractor implements ResultSetExtractor<List<UaaTipo>>{

	@Override
	public List<UaaTipo> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<UaaTipo> list = new ArrayList<UaaTipo>();
		while (rs.next()) {
			list.add(new UaaTipoRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}
		
		return list;
	}

}
