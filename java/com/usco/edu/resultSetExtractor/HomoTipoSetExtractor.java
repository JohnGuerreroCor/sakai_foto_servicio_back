package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.HomologacionTipo;
import com.usco.edu.rowMapper.HomologacionTipoRowMapper;

public class HomoTipoSetExtractor implements ResultSetExtractor<List<HomologacionTipo>>{

	@Override
	public List<HomologacionTipo> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<HomologacionTipo> list = new ArrayList<HomologacionTipo>();
		while (rs.next()) {
			list.add(new HomologacionTipoRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}
		
		return list;
	}

}
