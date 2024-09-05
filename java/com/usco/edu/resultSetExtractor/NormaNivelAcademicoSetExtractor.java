package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.NormaNivelAcademico;
import com.usco.edu.rowMapper.NormaNivelAcademicoRowMapper;

public class NormaNivelAcademicoSetExtractor implements ResultSetExtractor<List<NormaNivelAcademico>> {

	@Override
	public List<NormaNivelAcademico> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<NormaNivelAcademico> list = new ArrayList<NormaNivelAcademico>();
		while (rs.next()) {
			list.add(new NormaNivelAcademicoRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}

		return list;
	}
}