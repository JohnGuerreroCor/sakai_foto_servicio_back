package com.usco.edu.dao.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.usco.edu.dao.IProgramaDao;
import com.usco.edu.dto.ReportePrograma;
import com.usco.edu.entities.Convenio;
import com.usco.edu.entities.Estado;
import com.usco.edu.entities.Jornada;
import com.usco.edu.entities.Modalidad;
import com.usco.edu.entities.NivelAcademico;
import com.usco.edu.entities.Programa;
import com.usco.edu.entities.Sede;
import com.usco.edu.entities.Snies_nbc;
import com.usco.edu.entities.Uaa;
import com.usco.edu.resultSetExtractor.ConvenioSetExtractor;
import com.usco.edu.resultSetExtractor.EstadoProSetExtractor;
import com.usco.edu.resultSetExtractor.JornadaSetExtractor;
import com.usco.edu.resultSetExtractor.ModalidadSetExtractor;
import com.usco.edu.resultSetExtractor.NbcSetExtractor;
import com.usco.edu.resultSetExtractor.NivelAcademicoSetExtractor;
import com.usco.edu.resultSetExtractor.ProgramSimplSetExtractor;
import com.usco.edu.rowMapper.ProgramaRowMapper;
import com.usco.edu.rowMapper.ProgramaSimplRowMapper;
import com.usco.edu.util.AuditoriaJdbcTemplate;

@Repository
public class ProgramaDaoImpl implements IProgramaDao {

	@Autowired
	private AuditoriaJdbcTemplate jdbcComponent;

	@Autowired
	@Qualifier("JDBCTemplatePlanesConsulta")
	public JdbcTemplate jdbcTemplate;

	@Autowired
	@Qualifier("NamedJDBCTemplateEncuestasConsulta")
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	@Override
	public List<Programa> progromasBySede(Long sed_codigo, String userdb) {

		String sql = "SELECT * FROM programa p " + "INNER JOIN uaa ua on p.uaa_codigo = ua.uaa_codigo "
				+ "INNER JOIN sede s on s.sed_codigo = ua.sed_codigo "
				+ "INNER JOIN programa_estado pe on pe.pre_codigo = p.pro_estado "
				+ "INNER JOIN nivel_academico na on na.nia_codigo = p.nia_codigo "
				+ "INNER JOIN modalidad m on m.mod_codigo = p.mod_codigo "
				// +"WHERE ua.sed_codigo = ? and p.nia_codigo in (12,13,14) order by
				// ua.uaa_nombre desc ";
				+ "WHERE ua.sed_codigo = ? and ua.uaa_estado = 1 order by ua.uaa_nombre asc ";
		List<Programa> programas = null;
		try {
			programas = jdbcTemplate.query(sql, new Object[] { sed_codigo }, new ProgramSimplSetExtractor());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return programas;
	}

	@Override
	public List<Programa> progromasAll(String userdb) {

		String sql = "SELECT * FROM programa p " + "INNER JOIN uaa ua on p.uaa_codigo = ua.uaa_codigo "
				+ "INNER JOIN sede s on s.sed_codigo = p.sed_codigo "
				+ "INNER JOIN programa_estado pe on pe.pre_codigo = p.pro_estado "
				+ "INNER JOIN nivel_academico na on na.nia_codigo = p.nia_codigo "
				+ "INNER JOIN modalidad m on m.mod_codigo = p.mod_codigo "
				// +"WHERE p.nia_codigo in (12,13,14) and ua.uaa_estado = 1 order by
				// ua.uaa_nombre desc ";
				+ "WHERE ua.uaa_estado = 1 and ua.uaa_estado = 1 order by ua.uaa_nombre asc ";
		List<Programa> programas = null;
		try {
			programas = jdbcTemplate.query(sql, new ProgramSimplSetExtractor());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return programas;
	}

	@Override
	public Programa programaByUaa(Long uaaCodigo, String userdb) {

		String sql = "SELECT * FROM programa p " + "INNER JOIN uaa ua on p.uaa_codigo = ua.uaa_codigo "
				+ "INNER JOIN resolucion r on r.res_codigo = p.res_codigo "
				+ "INNER JOIN sede s on s.sed_codigo = p.sed_codigo "
				+ "INNER JOIN jornada j on j.jor_codigo = p.jor_codigo "
				+ "INNER JOIN nivel_academico na on na.nia_codigo = p.nia_codigo "
				+ "INNER JOIN modalidad m on m.mod_codigo = p.mod_codigo "
				+ "INNER JOIN convenio c on c.con_codigo = p.con_codigo "
				+ "INNER JOIN snies_nbc nbc on nbc.nbc_codigo = p.nbc_codigo "
				+ "INNER JOIN programa_estado pe on pe.pre_codigo = p.pro_estado "
				+ "left join tipo_normativa tn on tn.tin_codigo = r.tin_codigo "
				// +"WHERE p.nia_codigo in (12,13,14) and p.uaa_codigo = ? ";
				+ "WHERE p.uaa_codigo = ? ";
		Programa programa = null;
		try {
			programa = jdbcTemplate.queryForObject(sql, new Object[] { uaaCodigo }, new ProgramaRowMapper());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return programa;
	}

	@Override
	public List<Jornada> jornadasAll(String userdb) {

		String sql = "SELECT * FROM jornada ";
		List<Jornada> jornadas = null;
		try {
			jornadas = jdbcTemplate.query(sql, new JornadaSetExtractor());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jornadas;
	}

	@Override
	public List<Modalidad> modalidadAll(String userdb) {

		String sql = "SELECT * FROM modalidad ";

		List<Modalidad> modalidad = null;
		try {
			modalidad = jdbcTemplate.query(sql, new ModalidadSetExtractor());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return modalidad;
	}

	@Override
	public List<NivelAcademico> nivelAcademicoAll(String userdb) {

		String sql = "SELECT * FROM nivel_academico WHERE nia_estado = 1 ";
		List<NivelAcademico> nivelAcademico = null;
		try {
			nivelAcademico = jdbcTemplate.query(sql, new NivelAcademicoSetExtractor());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nivelAcademico;
	}

	@Override
	public List<Convenio> convenioAll(String userdb) {

		String sql = "SELECT * FROM convenio WHERE con_estado = 1";

		List<Convenio> convenios = null;
		try {
			convenios = jdbcTemplate.query(sql, new ConvenioSetExtractor());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return convenios;
	}

	@Override
	public Programa newPrograma(Programa programa, String userdb) {
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

		JdbcTemplate jdbcTemplate = jdbc.getJdbcTemplate();
		KeyHolder keyHolder = new GeneratedKeyHolder();

		if (programa.getNivelAcademico() == null) {
			String sql = "INSERT INTO programa (sed_codigo,uaa_codigo,pro_estado) values (?,?,?)";
			int result = 0;
			try {
				result = jdbcTemplate.update(new PreparedStatementCreator() {

					@Override
					public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
						PreparedStatement pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
						pstm.setLong(1, programa.getSede().getCodigo());
						pstm.setLong(2, programa.getUaa().getCodigo());
						pstm.setInt(3, 1);
						return pstm;
					}

				}, keyHolder);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					cerrarConexion(dataSource.getConnection());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (result > 0) {
				programa.setCodigo(keyHolder.getKey().longValue());
				Estado estado = new Estado();
				estado.setCodigo(1);
				programa.setEstado(estado);
			}

			return programa;

		} else {
			String sql = "INSERT INTO programa (nia_codigo,pro_registro_icfes,pro_registro_snies,pro_extension_snies,jor_codigo, "
					+ "res_codigo,pro_titulo_otorgado ,mod_codigo ,con_codigo,pro_horario,pro_estado,pro_propio,nbc_codigo, "
					+ "pro_calendario,uaa_codigo ,uaa_codigo_unificado ,sed_codigo ) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,?)";

			int result = 0;
			try {
				result = jdbcTemplate.update(new PreparedStatementCreator() {

					@Override
					public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
						PreparedStatement pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
						pstm.setLong(1, programa.getNivelAcademico().getCodigo());
						pstm.setString(2, programa.getRegistroIcfes());
						pstm.setString(3, programa.getRegistroSnies());
						pstm.setString(4, programa.getExtension());
						pstm.setLong(5, programa.getJornada().getCodigo());
						pstm.setLong(6, programa.getResolucion().getCodigo());
						pstm.setString(7, programa.getNombre());
						pstm.setLong(8, programa.getModalidad().getCodigo());
						pstm.setLong(9, programa.getConvenio().getCodigo());
						pstm.setString(10, programa.getHorario());
						pstm.setInt(11, programa.getEstado().getCodigo());
						pstm.setString(12, programa.getProPropio());
						pstm.setLong(13, programa.getNbc().getCodigo());
						pstm.setString(14, programa.getCalendario());
						pstm.setLong(15, programa.getUaa().getCodigo());
						pstm.setString(16, programa.getProUnificado());
						pstm.setLong(17, programa.getSede().getCodigo());
						return pstm;
					}

				}, keyHolder);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					cerrarConexion(dataSource.getConnection());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (result > 0) {
				programa.setCodigo(keyHolder.getKey().longValue());
				Estado estado = new Estado();
				estado.setCodigo(1);
				programa.setEstado(estado);
			}

			return programa;

		}

	}

	@Override
	public void updatePrograma(Programa programa, String userdb) {
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);
		JdbcTemplate jdbcTemplate = jdbc.getJdbcTemplate();

	
		String sql = "UPDATE programa SET nia_codigo = ?, pro_registro_icfes = ?, pro_registro_snies = ?, pro_extension_snies = ?, "
				+ " jor_codigo = ?, res_codigo = ?, pro_titulo_otorgado = ?, mod_codigo = ?, con_codigo = ?, pro_horario = ? , "
				+ " pro_estado = ? , pro_propio = ?, nbc_codigo = ?, pro_calendario = ?, uaa_codigo = ? , uaa_codigo_unificado = ?, "
				+ "cam_det_codigo=? WHERE pro_codigo = ?  ";
		try {
			jdbcTemplate.update(new PreparedStatementCreator() {

				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement pstm = con.prepareStatement(sql);
					pstm.setLong(1, programa.getNivelAcademico().getCodigo());
					pstm.setString(2, programa.getRegistroIcfes());
					pstm.setString(3, programa.getRegistroSnies());
					pstm.setString(4, programa.getExtension());
					pstm.setLong(5, programa.getJornada().getCodigo());
					pstm.setLong(6, programa.getResolucion().getCodigo());
					pstm.setString(7, programa.getNombre());
					pstm.setLong(8, programa.getModalidad().getCodigo());
					pstm.setLong(9, programa.getConvenio().getCodigo());
					pstm.setString(10, programa.getHorario());
					pstm.setInt(11, programa.getEstado().getCodigo());
					pstm.setString(12, programa.getProPropio());
					pstm.setLong(13, programa.getNbc().getCodigo());
					pstm.setString(14, programa.getCalendario());
					pstm.setLong(15, programa.getUaa().getCodigo());
					pstm.setString(16, programa.getProUnificado());

					pstm.setLong(18, programa.getCodigo());
					pstm.setInt(17, programa.getCampoDetallado().getCodigo());

					return pstm;
				}

			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
	public List<Estado> estadosPrograma(String userdb) {

		String sql = "SELECT * FROM programa_estado";
		List<Estado> estado = null;
		try {
			estado = jdbcTemplate.query(sql, new EstadoProSetExtractor());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return estado;
	}

	@Override
	public List<Snies_nbc> nbcAll(String userdb) {

		String sql = "SELECT nbc_codigo,ar.sar_nombre +' (' +nbc_nombre+')' as nbc_nombre, nbc.sar_codigo FROM snies_nbc nbc "
				+ "INNER JOIN snies_area ar on ar.sar_codigo = nbc.sar_codigo";
		List<Snies_nbc> nbc = null;
		try {
			nbc = jdbcTemplate.query(sql, new NbcSetExtractor());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nbc;
	}

	@Override
	public Programa findById(Long proCodigo, String userdb) {
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);
		JdbcTemplate jdbcTemplate = jdbc.getJdbcTemplate();

		String sql = "SELECT * FROM programa p INNER JOIN uaa ua on p.uaa_codigo = ua.uaa_codigo "
				+ "left JOIN sede s on s.sed_codigo = p.sed_codigo "
				+ "left JOIN jornada j on j.jor_codigo = p.jor_codigo "
				+ "left JOIN nivel_academico na on na.nia_codigo = p.nia_codigo "
				+ "left JOIN modalidad m on m.mod_codigo = p.mod_codigo "
				+ "left JOIN convenio c on c.con_codigo = p.con_codigo "
				+ "left JOIN snies_nbc nbc on nbc.nbc_codigo = p.nbc_codigo "
				+ "left JOIN programa_estado pe on pe.pre_codigo = p.pro_estado "
				+ "left JOIN resolucion r on r.res_codigo = p.res_codigo "
				+ "left join tipo_normativa tn on tn.tin_codigo = r.tin_codigo "
				+ "left join acreditacion.campo_detallado cd on cd.cam_det_codigo =p.cam_det_codigo "
				+ "left join acreditacion.campo_especifico ce on ce.cam_esp_codigo =cd.cam_esp_codigo "
				+ " left join acreditacion.campo_amplio ca on ca.cam_codigo = ce.cam_codigo "

				+ "WHERE p.pro_codigo = ? ";
//		Programa programa = null;
		try {
			return jdbcTemplate.queryForObject(sql, new Object[] { proCodigo }, new ProgramaRowMapper());

		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}

	}

	@Override
	public Programa findByIdSimple(Long proCodigo, String userdb) {

		String sql = "SELECT * FROM programa p " + "INNER JOIN uaa ua on p.uaa_codigo = ua.uaa_codigo "
				+ "INNER JOIN sede s on s.sed_codigo = p.sed_codigo "
				+ "INNER JOIN programa_estado pe on pe.pre_codigo = p.pro_estado " + "WHERE p.pro_codigo = ? ";
		Programa programa = null;
		try {
			programa = jdbcTemplate.queryForObject(sql, new Object[] { proCodigo }, new ProgramaSimplRowMapper());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return programa;
	}

	@Override
	public Programa programaSimpleByUaa(Long uaaCodigo, String userdb) {

		String sql = "SELECT * FROM programa p " + "INNER JOIN uaa ua on p.uaa_codigo = ua.uaa_codigo "
				+ "INNER JOIN sede s on s.sed_codigo = p.sed_codigo "
				+ "INNER JOIN programa_estado pe on pe.pre_codigo = p.pro_estado "
				// +"INNER JOIN nivel_academico na on na.nia_codigo = p.nia_codigo "
				// +"INNER JOIN modalidad m on m.mod_codigo = p.mod_codigo "
				+ "WHERE p.uaa_codigo = ? ";
		Programa programa = null;
		try {
			programa = jdbcTemplate.queryForObject(sql, new Object[] { uaaCodigo }, new ProgramaSimplRowMapper());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return programa;
	}

	@Override
	public boolean validarNivelAcademico(Long proCodigo, String userdb) {

		String sql = "select (nia_codigo) from programa where pro_codigo = ?";
		String result = null;
		try {
			result = jdbcTemplate.queryForObject(sql, new Object[] { proCodigo }, String.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result != null ? false : true;

	}

	@Override
	public int totalprogramas(String userdb) {
		;
		String sql = "SELECT COUNT(p.pro_codigo) FROM programa p "
				+ "INNER JOIN uaa ua on p.uaa_codigo = ua.uaa_codigo "
				+ "INNER JOIN sede s on s.sed_codigo = p.sed_codigo "
				+ "INNER JOIN programa_estado pe on pe.pre_codigo = p.pro_estado "
				+ "INNER JOIN nivel_academico na on na.nia_codigo = p.nia_codigo "
				+ "INNER JOIN modalidad m on m.mod_codigo = p.mod_codigo " + "WHERE ua.uaa_estado = 1 ; ";
		int total = 0;
		try {
			total = jdbcTemplate.queryForObject(sql, Integer.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return total;
	}

	@Override
	public List<Programa> programabySnies(String sniesCod, String userdb) {

		String sql = "SELECT * FROM programa p " + "INNER JOIN uaa ua on p.uaa_codigo = ua.uaa_codigo "
				+ "INNER JOIN sede s on s.sed_codigo = p.sed_codigo "
				+ "INNER JOIN programa_estado pe on pe.pre_codigo = p.pro_estado "
				+ "INNER JOIN nivel_academico na on na.nia_codigo = p.nia_codigo "
				+ "INNER JOIN modalidad m on m.mod_codigo = p.mod_codigo "
				// +"WHERE p.nia_codigo in (12,13,14) and ua.uaa_estado = 1 and
				// p.pro_registro_snies = ? ; ";
				+ "WHERE ua.uaa_estado = 1 and p.pro_registro_snies = ? ; ";
		List<Programa> programas = null;
		try {
			programas = jdbcTemplate.query(sql, new Object[] { sniesCod }, new ProgramSimplSetExtractor());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return programas;
	}

	@Override
	public List<Programa> progromasbyNivelAcademico(String userdb, int nivelAcademico) {

		String sql = "SELECT * FROM programa p " + "INNER JOIN uaa ua on p.uaa_codigo = ua.uaa_codigo "
				+ "INNER JOIN sede s on s.sed_codigo = p.sed_codigo "
				+ "INNER JOIN programa_estado pe on pe.pre_codigo = p.pro_estado "
				+ "INNER JOIN nivel_academico na on na.nia_codigo = p.nia_codigo "
				// +"INNER JOIN modalidad m on m.mod_codigo = p.mod_codigo "
				// +"WHERE p.mod_codigo = ? and p.nia_codigo in (12,13,14) order by
				// ua.uaa_nombre desc ";
				+ "WHERE na.nia_codigo = ? order by ua.uaa_nombre asc ";
		List<Programa> programas = null;
		try {
			programas = jdbcTemplate.query(sql, new Object[] { nivelAcademico }, new ProgramSimplSetExtractor());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return programas;
	}

	@Override
	public List<Programa> progromasbyModalidad(String userdb, int modalidad) {

		String sql = "SELECT * FROM programa p " + "INNER JOIN uaa ua on p.uaa_codigo = ua.uaa_codigo "
				+ "INNER JOIN sede s on s.sed_codigo = p.sed_codigo "
				+ "INNER JOIN programa_estado pe on pe.pre_codigo = p.pro_estado "
				// +"INNER JOIN nivel_academico na on na.nia_codigo = p.nia_codigo "
				+ "INNER JOIN modalidad m on m.mod_codigo = p.mod_codigo "
				// +"WHERE p.mod_codigo = ? and p.nia_codigo in (12,13,14) order by
				// ua.uaa_nombre desc ";
				+ "WHERE p.mod_codigo = ? order by ua.uaa_nombre asc ";
		List<Programa> programas = null;
		try {
			programas = jdbcTemplate.query(sql, new Object[] { modalidad }, new ProgramSimplSetExtractor());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return programas;
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

	@Override
	public ReportePrograma datosReporte(Long proCodigo) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("codigo", proCodigo);

		String sql = "select pro_registro_snies, pro_registro_icfes , CASE"
				+ "    WHEN pro_registro_snies > 1 or pro_registro_snies != null THEN 'ACTIVO'"
				+ "    ELSE 'DESACTIVO' END AS estado , " + "	CASE" + "    WHEN pro_estado = 01 THEN 'SI'"
				+ "    ELSE 'NO' END AS oferta, s.sed_nombre,m.mod_nombre , na.nia_nombre,u.uaa_nombre,(select uaa_nombre from uaa where uaa_codigo = u.uaa_dependencia) as dependencia, "
				+ "	CASE " + "    WHEN na.nia_codigo in (12,13,14,19) THEN 'POSGRADO' "
				+ "    ELSE 'PREGRADO' END AS formacion " + "	from programa "
				+ "	INNER JOIN uaa u on u.uaa_codigo = p.uaa_codigo "
				+ "	INNER JOIN sede s on s.sed_codigo = u.sed_codigo "
				+ "	INNER JOIN modalidad m on m.mod_codigo = p.mod_codigo "
				+ "	INNER JOIN nivel_academico na on na.nia_codigo = p.nia_codigo " + "	where pro_codigo=:codigo";

		List<ReportePrograma> reportePrograma = namedJdbcTemplate.query(sql, parameter,
				new RowMapper<ReportePrograma>() {

					@Override
					public ReportePrograma mapRow(ResultSet rs, int rowNum) throws SQLException {

						Programa programa = new Programa();
						programa.setRegistroSnies(rs.getString("pro_registro_snies"));
						programa.setRegistroIcfes(rs.getString("pro_registro_icfes"));

						Uaa uaa = new Uaa();
						uaa.setNombre(rs.getString("uaa_nombre"));

						Sede sede = new Sede();
						sede.setNombre(rs.getString("sed_nombre"));

						NivelAcademico nv = new NivelAcademico();
						nv.setNombre(rs.getString("nia_nombre"));

						Modalidad modalidad = new Modalidad();
						modalidad.setNombre(rs.getString("mod_nombre"));

						ReportePrograma rp = new ReportePrograma();
						rp.setUaaDependencia(rs.getString("dependencia"));
						rp.setEstado(rs.getString("estado"));
						rp.setFormacion(rs.getString("formacion"));
						rp.setModalidad(modalidad);
						rp.setPrograma(programa);
						rp.setUaa(uaa);
						rp.setNivelAcademico(nv);

						return rp;
					}

				});

		return reportePrograma.get(0);

	}

}
