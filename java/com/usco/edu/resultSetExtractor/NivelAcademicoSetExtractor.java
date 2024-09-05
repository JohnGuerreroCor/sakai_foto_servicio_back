package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.NivelAcademico;
import com.usco.edu.rowMapper.NivelAcademicoRowMapper;

public class NivelAcademicoSetExtractor implements ResultSetExtractor<List<NivelAcademico>> {

	@Override
	public List<NivelAcademico> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<NivelAcademico> list = new ArrayList<NivelAcademico>();
		while (rs.next()) {
			list.add(new NivelAcademicoRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}
		
		return list;
	}

}
