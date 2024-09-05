package com.usco.edu.dao.daoImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.usco.edu.dao.ICamposDao;
import com.usco.edu.entities.CampoAmplio;
import com.usco.edu.entities.CampoDetallado;
import com.usco.edu.entities.CampoEspecifico;
import com.usco.edu.resultSetExtractor.CamAmplSetExtractor;
import com.usco.edu.resultSetExtractor.CamDetSetExtractor;
import com.usco.edu.resultSetExtractor.CamExpSetExtractor;
import com.usco.edu.util.AuditoriaJdbcTemplate;

@Repository
public class CamposDaoImpl implements ICamposDao {

	@Autowired
	private AuditoriaJdbcTemplate jdbcComponent;

	@Override
	public List<CampoAmplio> camposAmplios(String userdb) {
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);
		JdbcTemplate jdbcTemplate = jdbc.getJdbcTemplate();
		String sql = "SELECT * FROM acreditacion.campo_amplio where cam_estado=1 ";
		return jdbcTemplate.query(sql, new CamAmplSetExtractor());
	}

	@Override
	public List<CampoDetallado> camposDetallados(String userdb) {
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);
		JdbcTemplate jdbcTemplate = jdbc.getJdbcTemplate();
		String sql = "SELECT * FROM acreditacion.campo_detallado cd join acreditacion.campo_especifico ce on cd.cam_esp_codigo=ce.cam_esp_codigo "
				+ "join acreditacion.campo_amplio ca on ce.cam_codigo=ca.cam_codigo where cam_det_estado=1";
		return jdbcTemplate.query(sql, new CamDetSetExtractor());
	}

	@Override
	public List<CampoDetallado> camposDetalladosByEspecifico(String userdb, int ce) {
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);
		JdbcTemplate jdbcTemplate = jdbc.getJdbcTemplate();
		String sql = "SELECT * FROM acreditacion.campo_detallado cd join acreditacion.campo_especifico ce on cd.cam_esp_codigo=ce.cam_esp_codigo "
				+ "join acreditacion.campo_amplio ca on ce.cam_codigo=ca.cam_codigo where cam_det_estado=1 AND cd.cam_esp_codigo="
				+ ce + "";
		return jdbcTemplate.query(sql, new CamDetSetExtractor());
	}

	@Override
	public CampoDetallado camposDetalladosById(String userdb, int id) {
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);
		JdbcTemplate jdbcTemplate = jdbc.getJdbcTemplate();
		String sql = "SELECT * FROM acreditacion.campo_detallado cd join acreditacion.campo_especifico ce on cd.cam_esp_codigo=ce.cam_esp_codigo "
				+ "join acreditacion.campo_amplio ca on ce.cam_codigo=ca.cam_codigo where cam_det_estado=1 AND cd.cam_det_codigo="
				+ id + "";
		return jdbcTemplate.query(sql, new CamDetSetExtractor()).get(0);
	}

	@Override
	public List<CampoEspecifico> camposExpecificos(String userdb) {
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);
		JdbcTemplate jdbcTemplate = jdbc.getJdbcTemplate();
		String sql = "SELECT * FROM acreditacion.campo_especifico ce join acreditacion.campo_amplio ca on"
				+ " ce.cam_codigo=ca.cam_codigo where cam_esp_estado=1";
		return jdbcTemplate.query(sql, new CamExpSetExtractor());
	}

	@Override
	public List<CampoEspecifico> camposExpecificosByAmplio(String userdb, int ca) {
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);
		JdbcTemplate jdbcTemplate = jdbc.getJdbcTemplate();
		String sql = "SELECT * FROM acreditacion.campo_especifico ce join acreditacion.campo_amplio ca on"
				+ " ce.cam_codigo=ca.cam_codigo where cam_esp_estado=1 AND ce.cam_codigo="
				+ ca + "";
		return jdbcTemplate.query(sql, new CamExpSetExtractor());
	}

	@Override
	public int createCampoAmplio(String userdb, CampoAmplio ca) {

		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

		KeyHolder keyHolder = new GeneratedKeyHolder();
		String sql = "INSERT INTO acreditacion.campo_amplio (cam_nombre, codigo_cine) VALUES(:nombre,:cine);";

		try {

			MapSqlParameterSource parameter = new MapSqlParameterSource();
			parameter.addValue("nombre", ca.getNombre());
			parameter.addValue("cine", ca.getCodigoCine());
			jdbc.update(sql, parameter, keyHolder);
			return keyHolder.getKey().intValue();

		} catch (Exception e) {

			e.printStackTrace();
			return 0;
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
	public int updateCampoAmplio(String userdb, CampoAmplio ca) {

		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

		String sql = "UPDATE acreditacion.campo_amplio SET cam_estado=:estado, cam_nombre=:nombre, codigo_cine=:cine WHERE cam_codigo=:codigo";

		try {

			MapSqlParameterSource parameter = new MapSqlParameterSource();
			parameter.addValue("nombre", ca.getNombre());
			parameter.addValue("cine", ca.getCodigoCine());
			parameter.addValue("codigo", ca.getCodigo());
			parameter.addValue("estado", ca.getEstado());

			return jdbc.update(sql, parameter);
		} catch (Exception e) {

			e.printStackTrace();
			return 0;
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
	public int updateCampoEspecifico(String userdb, CampoEspecifico ce) {

		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

		String sql = "UPDATE acreditacion.campo_especifico SET cam_esp_nombre=:nombre,cam_esp_estado=:estado,"
				+ " cam_codigo=:amplio, codigo_cine=:cine WHERE cam_esp_codigo=:codigo";

		try {

			MapSqlParameterSource parameter = new MapSqlParameterSource();
			parameter.addValue("amplio", ce.getCampoAmplio().getCodigo());
			parameter.addValue("nombre", ce.getNombre());
			parameter.addValue("cine", ce.getCodigoCine());
			parameter.addValue("codigo", ce.getCodigo());
			parameter.addValue("estado", ce.getEstado());

			return jdbc.update(sql, parameter);
		} catch (Exception e) {

			e.printStackTrace();
			return 0;
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
	public int updateCampoDetallado(String userdb, CampoDetallado cd) {

		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

		String sql = "UPDATE acreditacion.campo_detallado SET cam_det_nombre=:nombre,"
				+ " cam_det_estado=:estado, codigo_cine=:cine, cam_esp_codigo=:esp WHERE cam_det_codigo=:codigo";

		try {

			MapSqlParameterSource parameter = new MapSqlParameterSource();
			parameter.addValue("esp", cd.getCampoEspecifico().getCodigo());
			parameter.addValue("nombre", cd.getNombre());
			parameter.addValue("cine", cd.getCodigoCine());
			parameter.addValue("codigo", cd.getCodigo());
			parameter.addValue("estado", cd.getEstado());

			return jdbc.update(sql, parameter);
		} catch (Exception e) {

			e.printStackTrace();
			return 0;
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
	public int createCampoEspecifico(String userdb, CampoEspecifico ce) {

		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

		KeyHolder keyHolder = new GeneratedKeyHolder();
		String sql = "INSERT INTO acreditacion.campo_especifico (cam_esp_nombre,cam_codigo,codigo_cine) VALUES(:nombre,:amplio,:cine);";

		try {

			MapSqlParameterSource parameter = new MapSqlParameterSource();
			parameter.addValue("nombre", ce.getNombre());
			parameter.addValue("cine", ce.getCodigoCine());
			parameter.addValue("amplio", ce.getCampoAmplio().getCodigo());
			jdbc.update(sql, parameter, keyHolder);

			return keyHolder.getKey().intValue();

		} catch (Exception e) {

			e.printStackTrace();
			return 0;
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
	public int createCampoDetallado(String userdb, CampoDetallado cd) {

		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

		KeyHolder keyHolder = new GeneratedKeyHolder();
		String sql = "INSERT INTO acreditacion.campo_detallado (cam_det_nombre,cam_esp_codigo, codigo_cine) VALUES(:nombre,:esp,:cine);";

		try {

			MapSqlParameterSource parameter = new MapSqlParameterSource();
			parameter.addValue("nombre", cd.getNombre());
			parameter.addValue("cine", cd.getCodigoCine());
			parameter.addValue("esp", cd.getCampoEspecifico().getCodigo());

			return jdbc.update(sql, parameter, keyHolder);

		} catch (Exception e) {

			e.printStackTrace();
			return 0;
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
