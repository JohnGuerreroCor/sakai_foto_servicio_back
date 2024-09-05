package com.usco.edu.dao.daoImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.usco.edu.dao.INormaNivelAcademicoDao;
import com.usco.edu.entities.NormaNivelAcademico;
import com.usco.edu.resultSetExtractor.NormaNivelAcademicoSetExtractor;
import com.usco.edu.util.AuditoriaJdbcTemplate;

@Repository
public class NormaNivelAcreditacionDaoImpl implements INormaNivelAcademicoDao {

	@Autowired
	private AuditoriaJdbcTemplate jdbcComponent;

	@Autowired
	@Qualifier("JDBCTemplatePlanesConsulta")
	public JdbcTemplate jdbcTemplate;

	@Override
	public List<NormaNivelAcademico> find(String userdb) {

		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);
		JdbcTemplate jdbcTemplate = jdbc.getJdbcTemplate();
		String sql = "SELECT * FROM acreditacion.norma_nivel_academico where nna_estado=1 ";
		return jdbcTemplate.query(sql, new NormaNivelAcademicoSetExtractor());
	}

	@Override
	public List<NormaNivelAcademico> findbyId(Long codigo, String userdb) {
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);
		JdbcTemplate jdbcTemplate = jdbc.getJdbcTemplate();
		String sql = "SELECT * FROM acreditacion.norma_nivel_academico where nna_estado=1 AND nna_codigo=" + codigo
				+ " ";
		return jdbcTemplate.query(sql, new NormaNivelAcademicoSetExtractor());
	}

	@Override
	public void create(NormaNivelAcademico na, String userdb) {
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

		KeyHolder keyHolder = new GeneratedKeyHolder();
		String sql = "INSERT INTO acreditacion.norma_nivel_academico (nna_nombre,nia_codigo,noa_codigo) VALUES(	:nombre,:nva,:norma);";

		try {

			MapSqlParameterSource parameter = new MapSqlParameterSource();
			parameter.addValue("nombre", na.getNombre());
			parameter.addValue("nva", na.getNivelAcademico().getCodigo());
			parameter.addValue("norma", na.getNorma().getCodigo());

			jdbc.update(sql, parameter, keyHolder);

		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			try {
				cerrarConexion(dataSource.getConnection());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public void update(NormaNivelAcademico na, String userdb) {
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

		String sql = "UPDATE acreditacion.norma_nivel_academico SET nna_nombre=:nombre,"
				+ " nna_estado=:estado, noa_codigo=:norma, nia_codigo=:nia WHERE nna_codigo=:codigo";

		try {

			MapSqlParameterSource parameter = new MapSqlParameterSource();
			parameter.addValue("nombre", na.getNombre());
			parameter.addValue("nia", na.getNivelAcademico().getCodigo());
			parameter.addValue("norma", na.getNorma().getCodigo());
			parameter.addValue("codigo", na.getCodigo());
			parameter.addValue("estado", na.getEstado());

			jdbc.update(sql, parameter);

		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			try {
				cerrarConexion(dataSource.getConnection());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private void cerrarConexion(Connection con) {
		if (con == null)
			return;

		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
