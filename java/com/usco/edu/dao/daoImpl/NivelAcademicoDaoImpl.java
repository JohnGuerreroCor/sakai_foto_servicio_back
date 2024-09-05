package com.usco.edu.dao.daoImpl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.usco.edu.dao.INivelAcademicoDao;
import com.usco.edu.entities.NivelAcademico;
import com.usco.edu.resultSetExtractor.NivelAcademicoSetExtractor;
import com.usco.edu.util.AuditoriaJdbcTemplate;

@Repository
public class NivelAcademicoDaoImpl implements INivelAcademicoDao {

	@Autowired
	private AuditoriaJdbcTemplate jdbcComponent;

	@Override
	public List<NivelAcademico> findPregradoPostgrado(String userdb) {

		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);
		JdbcTemplate jdbcTemplate = jdbc.getJdbcTemplate();
		String sql = "select  * from nivel_academico where nat_codigo in(1,2) and nia_estado =1 ";
		return jdbcTemplate.query(sql, new NivelAcademicoSetExtractor());
	}

}
