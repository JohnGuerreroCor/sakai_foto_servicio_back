package com.usco.edu.dao.daoImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
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

import com.usco.edu.dao.INormaAcreditacionDao;
import com.usco.edu.entities.NormaAcreditacion;
import com.usco.edu.resultSetExtractor.NormaAcreditacionSetExtractor;
import com.usco.edu.util.AuditoriaJdbcTemplate;

@Repository
public class NormaAcreditacionDaoImpl implements INormaAcreditacionDao {

	@Autowired
	private AuditoriaJdbcTemplate jdbcComponent;

	@Autowired
	@Qualifier("JDBCTemplatePlanesConsulta")
	public JdbcTemplate jdbcTemplate;

	@Override
	public List<NormaAcreditacion> find(String userdb) {
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);
		JdbcTemplate jdbcTemplate = jdbc.getJdbcTemplate();
		String sql = "SELECT * FROM acreditacion.norma_acreditacion where noa_estado=1 ";
		return jdbcTemplate.query(sql, new NormaAcreditacionSetExtractor());
	}

	@Override
	public List<NormaAcreditacion> findbyId(int codigo, String userdb) {
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);
		JdbcTemplate jdbcTemplate = jdbc.getJdbcTemplate();
		String sql = "SELECT * FROM acreditacion.norma_acreditacion where noa_estado=1 AND noa_codigo=" + codigo + "";
		return jdbcTemplate.query(sql, new NormaAcreditacionSetExtractor());
	}

	@Override
	public void create(NormaAcreditacion na, String userdb) {

		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

		KeyHolder keyHolder = new GeneratedKeyHolder();
		String sql = "INSERT INTO acreditacion.norma_acreditacion (noa_nombre,noa_fecha_fin,noa_fecha_inicio,noa_ruta) VALUES(:nombre,:fin,:inicio,:ruta);";

		try {

			MapSqlParameterSource parameter = new MapSqlParameterSource();
			parameter.addValue("nombre", na.getNombre());
			parameter.addValue("fin", na.getFin(), Types.DATE);
			parameter.addValue("inicio", na.getInicio());
			parameter.addValue("ruta", na.getPdf());

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
	public void update(NormaAcreditacion na, String userdb) {
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

		String sql = "UPDATE acreditacion.norma_acreditacion SET noa_nombre=:nombre,"
				+ " noa_estado=:estado, noa_fecha_inicio=:inicio, noa_fecha_fin=:fin WHERE noa_codigo=:codigo";

		try {

			MapSqlParameterSource parameter = new MapSqlParameterSource();

			parameter.addValue("nombre", na.getNombre());
			parameter.addValue("fin", na.getFin(), Types.DATE);
			parameter.addValue("inicio", na.getInicio());
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
