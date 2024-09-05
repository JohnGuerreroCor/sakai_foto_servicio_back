package com.usco.edu.dao.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.usco.edu.dao.IAsignaturaDao;
import com.usco.edu.entities.Asignatura;
import com.usco.edu.entities.Paa;
import com.usco.edu.resultSetExtractor.AsigSetExtractor;
import com.usco.edu.resultSetExtractor.AsignaturaSetExtractor;
import com.usco.edu.rowMapper.AsignaturaRowMapper;
import com.usco.edu.util.AuditoriaJdbcTemplate;

@Repository
public class AsignaturaDaoImpl implements IAsignaturaDao{
	@Autowired
	private AuditoriaJdbcTemplate jdbcComponent;
	
	@Autowired
	@Qualifier("JDBCTemplatePlanesConsulta")
	public JdbcTemplate jdbcTemplate;

	@Override
	public List<Asignatura> findAll(String userdb) {

		System.out.println("entramos a mirar las asignaturas");
		String sql = "SELECT * FROM asignatura a "
				+ "inner join uaa u on u.uaa_codigo = a.uaa_codigo "
				+ "inner join uaa_tipo ut on ut.uat_codigo = u.uat_codigo "
				+ "inner join sede se on se.sed_codigo = u.sed_codigo "
				+ "inner join programa p on p.uaa_codigo = u.uaa_codigo "
				+ "WHERE a.asi_estado = 1 and u.uaa_estado = 1 ";
		List<Asignatura> asignaturas = null;
		try {
			asignaturas = jdbcTemplate.query(sql, new AsignaturaSetExtractor());
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return asignaturas;
		
		//return jdbcTemplate.query(sql, new AsignaturaSetExtractor());
	}

	@Override
	public List<Asignatura> obtenerPrerrequisitos(int pla_codigo, int asi_codigo, String userdb) {
		
		String sql = "SELECT * FROM asignatura asi " 
				+"inner join prerrequisito p on p.asi_requisito = asi.asi_codigo "
				+"inner join uaa u on u.uaa_codigo = asi.uaa_codigo " 
				+"inner join uaa_tipo ut on ut.uat_codigo = u.uat_codigo "
				+"inner join sede se on se.sed_codigo = u.sed_codigo "  
				+"WHERE p.pla_codigo = ? and p.asi_codigo = ? ";
		
		List<Asignatura> asignaturas = null;
		try {
			asignaturas = jdbcTemplate.query(sql,new Object[] {pla_codigo , asi_codigo}, new AsignaturaSetExtractor());
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return asignaturas;
	}

	@Override
	public List<Asignatura> obtenerEquivalencias(int pla_codigo, int asi_codigo, String userdb) {

		String sql = "SELECT * FROM asignatura asi " 
				+"inner join equivalencia e on e.asi_equivalencia = asi.asi_codigo "
				+"inner join uaa u on u.uaa_codigo = asi.uaa_codigo " 
				+"inner join uaa_tipo ut on ut.uat_codigo = u.uat_codigo "
				+"inner join sede se on se.sed_codigo = u.sed_codigo "  
				+"WHERE e.pla_codigo = ? and e.asi_codigo = ? ";
		
		List<Asignatura> asignaturas = null;
		try {
			asignaturas = jdbcTemplate.query(sql,new Object[] {pla_codigo , asi_codigo}, new AsignaturaSetExtractor());
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return asignaturas;
	}

	@Override
	public String obtenerPrerrequisitoCredito(int pla_codigo, int asi_codigo, String userdb) {
		
		String sql = "SELECT COALESCE(SUM(p.pre_creditos), 0) FROM prerrequisito p "
				+ "WHERE p.pla_codigo = ? and p.asi_codigo = ? ";
		
		String precreditos = null;
		try {
			precreditos = jdbcTemplate.queryForObject(sql, new Object[] { pla_codigo, asi_codigo}, String.class);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return precreditos;
	}

	@Override
	public void crearPrerrequisito(Paa paa, int asignatura,String userdb) {
		//NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplate(userdb);
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);
		JdbcTemplate jdbcTemplate = jdbc.getJdbcTemplate();
		
		String sql = "INSERT INTO prerrequisito (pla_codigo, asi_codigo, asi_requisito) VALUES (?, ?, ?)";
		
		try {
			jdbcTemplate.update(new PreparedStatementCreator() {

				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement pstm = con.prepareStatement(sql);
					pstm.setInt(1, paa.getPlan());
					pstm.setInt(2, paa.getAsignatura().getCodigo());
					pstm.setInt(3, asignatura);

					return pstm;
				}
				
			});
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				cerrarConexion(dataSource.getConnection());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public void crearEquivalencia(Paa paa, int asignatura, String userdb) {
	//	NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplate(userdb);
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);
		JdbcTemplate jdbcTemplate = jdbc.getJdbcTemplate();
		
		String sql = "INSERT INTO equivalencia (pla_codigo, asi_codigo, asi_equivalencia) VALUES (?, ?, ?)";
		
		try {
			int result = jdbcTemplate.update(new PreparedStatementCreator() {

				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement pstm = con.prepareStatement(sql);
					pstm.setInt(1, paa.getPlan());
					pstm.setInt(2, paa.getAsignatura().getCodigo());
					pstm.setInt(3, asignatura);

					return pstm;
				}
				
			}); System.out.println("result Equivalencias: "+result);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				cerrarConexion(dataSource.getConnection());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	}

	@Override
	public List<Asignatura> AsignaturasPrerrequisitos(int pla_codigo, String userdb) {
		
		String sql = "SELECT * FROM asignatura a " 
				+"inner join plan_academico_asignatura paa on paa.asi_codigo = a.asi_codigo " 
				+"inner join uaa u on u.uaa_codigo = a.uaa_codigo " 
				+"inner join uaa_tipo ut on ut.uat_codigo = u.uat_codigo "
				+"inner join sede s on s.sed_codigo = u.sed_codigo WHERE paa.pla_codigo = ? and paa.paa_estado = 1";
		List<Asignatura> asignaturas = null;
		try {
			asignaturas = jdbcTemplate.query(sql,new Object[] {pla_codigo }, new AsignaturaSetExtractor());
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return asignaturas;
	}

	@Override
	public Asignatura findById(String userdb, int codigo) {
		String sql = "SELECT * FROM asignatura a " 
				+"INNER JOIN uaa u on u.uaa_codigo = a.uaa_codigo " 
				+"INNER JOIN uaa_tipo ut on ut.uat_codigo = u.uat_codigo " 
				+"INNER JOIN sede se on se.sed_codigo = u.sed_codigo " 
			//	+"INNER JOIN programa p on p.uaa_codigo = u.uaa_codigo " 
				+"INNER JOIN snies_nbc n on n.nbc_codigo = a.nbc_codigo "
				+"INNER JOIN caracter c on c.car_codigo = a.car_codigo " 
				+"INNER JOIN nucleo nu on nu.nuc_codigo = a.nuc_codigo "
				+"WHERE u.uaa_estado = 1 and a.asi_codigo = ? ";
		Asignatura asignatura = null;
		try {
			asignatura = jdbcTemplate.queryForObject(sql, new Object[] { codigo }, new AsignaturaRowMapper());
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
		}
		
		return asignatura;
	}

	@Override
	public List<Asignatura> findbyName(String userdb, String asi_nombre) {

		String sql = "SELECT * FROM asignatura a " 
				+"INNER JOIN uaa u on u.uaa_codigo = a.uaa_codigo " 
				+"INNER JOIN uaa_tipo ut on ut.uat_codigo = u.uat_codigo " 
				+"INNER JOIN sede se on se.sed_codigo = u.sed_codigo " 
			//	+"INNER JOIN programa p on p.uaa_codigo = u.uaa_codigo " 
				+"INNER JOIN snies_nbc n on n.nbc_codigo = a.nbc_codigo "
				+"INNER JOIN caracter c on c.car_codigo = a.car_codigo " 
				+"INNER JOIN nucleo nu on nu.nuc_codigo = a.nuc_codigo "
				+"WHERE u.uaa_estado = 1 and a.asi_nombre like ? ";
		
		List<Asignatura> asignaturas = null;
		try {
			asignaturas = jdbcTemplate.query(sql, new Object[] {  "%" + asi_nombre + "%" }, new AsigSetExtractor());
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
		}
		
		return asignaturas;
	}

	@Override
	public void crearPrerrequisitoCredito(Paa paa, int creditos, String userdb) {
	//	NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplate(userdb);
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);
		JdbcTemplate jdbcTemplate = jdbc.getJdbcTemplate();
		
		String sql = "INSERT INTO prerrequisito (pla_codigo, asi_codigo, pre_creditos) VALUES (?, ?, ?)";
		try {
			jdbcTemplate.update(new PreparedStatementCreator() {

				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement pstm = con.prepareStatement(sql);
					pstm.setInt(1, paa.getPlan());
					pstm.setInt(2, paa.getAsignatura().getCodigo());
					pstm.setInt(3, creditos);

					return pstm;
				}
				
			});
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				cerrarConexion(dataSource.getConnection());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public List<Asignatura> findbyUaa(String userdb, int uaa) {

		
		String sql = "SELECT * FROM asignatura a " 
				+"INNER JOIN uaa u on u.uaa_codigo = a.uaa_codigo " 
				+"INNER JOIN uaa_tipo ut on ut.uat_codigo = u.uat_codigo " 
				+"INNER JOIN sede se on se.sed_codigo = u.sed_codigo " 
				+"INNER JOIN snies_nbc n on n.nbc_codigo = a.nbc_codigo "
				+"INNER JOIN caracter c on c.car_codigo = a.car_codigo " 
				+"INNER JOIN nucleo nu on nu.nuc_codigo = a.nuc_codigo "
				+"WHERE  u.uaa_estado = 1 and a.uaa_codigo = ? ";
		List<Asignatura> asignaturas = null;
		try {
			asignaturas = jdbcTemplate.query(sql, new Object[] {  uaa }, new AsigSetExtractor());
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return asignaturas;

	}

	@Override
	public Asignatura crearAsignatura(Asignatura asignatura, String userdb) {
		//NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplate(userdb);
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);
		JdbcTemplate jdbcTemplate = jdbc.getJdbcTemplate();
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		String sql = "INSERT INTO asignatura (asi_nombre, asi_nombre_corto, asi_nombre_impresion, uaa_codigo, asi_publicar "
			+   ",asi_creditos, asi_creditos_teoria, asi_creditos_practica, car_codigo, asi_trabajo_presencial, "  
			+	"asi_trabajo_independiente, asi_estado, nuc_codigo, nbc_codigo, asi_extramuro , asi_fecha_actualizacion , for_codigo ) " 
			+	"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		int result = 0;
		try {
			 result = jdbcTemplate.update(new PreparedStatementCreator() {

				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement pstm = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
					pstm.setString(1, asignatura.getNombre());
					pstm.setString(2, asignatura.getNombreCorto());
					pstm.setString(3, asignatura.getNombreImpresion());
					pstm.setLong(4, asignatura.getUaa().getCodigo());
					pstm.setString(5, asignatura.getPublicar());
					pstm.setInt(6, asignatura.getCreditos());
					pstm.setInt(7, asignatura.getCreditos_teoricos());
					pstm.setInt(8, asignatura.getCreditos_practicos());
					pstm.setString(9, asignatura.getCaracter().getCodigo());
					pstm.setString(10, asignatura.getTrabajo_presencial());
					pstm.setString(11, asignatura.getTrabajo_independiente());
					pstm.setInt(12,1);
					pstm.setLong(13, asignatura.getNucleo().getCodigo());
					pstm.setInt(14, asignatura.getNbc().getCodigo());
					pstm.setString(15, asignatura.getExtramuro());
					pstm.setTimestamp(16, new Timestamp(new Date().getTime()));
					pstm.setInt(17,1);
					
					return pstm;
				}
				
			},keyHolder);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				cerrarConexion(dataSource.getConnection());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	
		if(result > 0) {
			asignatura.setCodigo(keyHolder.getKey().intValue());
		}
		
		return asignatura;
	}

	@Override
	public boolean updateAsignatura(Asignatura asignatura, String userdb) {
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);
		JdbcTemplate jdbcTemplate = jdbc.getJdbcTemplate();
		
		String sql = "	UPDATE asignatura set asi_nombre = ? ,asi_nombre_corto = ? ,asi_nombre_impresion = ? ,uaa_codigo = ? ,asi_publicar= ? " + 
				", asi_creditos = ? ,asi_creditos_teoria = ? ,asi_creditos_practica = ?, car_codigo = ?, asi_trabajo_presencial = ? " + 
				", asi_trabajo_independiente = ? , asi_estado = ? , nuc_codigo = ? ,nbc_codigo = ? , asi_extramuro = ?, " + 
				"asi_fecha_actualizacion = ?  WHERE asi_codigo = ? ";
		int result = 0;
		try {
			 result = jdbcTemplate.update(new PreparedStatementCreator() {

				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement pstm = con.prepareStatement(sql);
					pstm.setString(1, asignatura.getNombre());
					pstm.setString(2, asignatura.getNombreCorto());
					pstm.setString(3, asignatura.getNombreImpresion());
					pstm.setLong(4, asignatura.getUaa().getCodigo());
					pstm.setString(5, asignatura.getPublicar());
					pstm.setInt(6, asignatura.getCreditos());
					pstm.setInt(7, asignatura.getCreditos_teoricos());
					pstm.setInt(8, asignatura.getCreditos_practicos());
					pstm.setString(9, asignatura.getCaracter().getCodigo());
					pstm.setString(10, asignatura.getTrabajo_presencial());
					pstm.setString(11, asignatura.getTrabajo_independiente());
					pstm.setInt(12, asignatura.getEstado());
					pstm.setLong(13, asignatura.getNucleo().getCodigo());
					pstm.setInt(14, asignatura.getNbc().getCodigo());
					pstm.setString(15, asignatura.getExtramuro());
					pstm.setTimestamp(16, new Timestamp(new Date().getTime()));
					pstm.setInt(17, asignatura.getCodigo());
			
					
					return pstm;
				}
				
			}); 
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				cerrarConexion(dataSource.getConnection());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return result > 0 ? true : false;
	}

	@Override
	public List<Asignatura> findbyNombreCorto(String userdb, String nombreCorto) {

		String sql = "SELECT * FROM asignatura a " 
				+"INNER JOIN uaa u on u.uaa_codigo = a.uaa_codigo " 
				+"INNER JOIN uaa_tipo ut on ut.uat_codigo = u.uat_codigo " 
				+"INNER JOIN sede se on se.sed_codigo = u.sed_codigo " 
				+"INNER JOIN snies_nbc n on n.nbc_codigo = a.nbc_codigo "
				+"INNER JOIN caracter c on c.car_codigo = a.car_codigo " 
				+"INNER JOIN nucleo nu on nu.nuc_codigo = a.nuc_codigo "
				+"WHERE u.uaa_estado = 1 and a.asi_nombre_corto like ? ";
		List<Asignatura> asignaturas = null;
		try {
			asignaturas = jdbcTemplate.query(sql, new Object[] {  "%" + nombreCorto + "%" }, new AsigSetExtractor());
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return asignaturas;
	}

	@Override
	public void eliminarEquivalencia(Paa paa, int asignatura, String userdb) {
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);
		JdbcTemplate jdbcTemplate = jdbc.getJdbcTemplate();
		
		String sql = "DELETE From equivalencia where pla_codigo = ? and asi_codigo = ? and asi_equivalencia = ? ";
		try {
			jdbcTemplate.update(new PreparedStatementCreator() {

				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement pstm = con.prepareStatement(sql);
					pstm.setInt(1, paa.getPlan());
					pstm.setInt(2, paa.getAsignatura().getCodigo());
					pstm.setInt(3, asignatura);

					return pstm;
				}
				
			});
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				cerrarConexion(dataSource.getConnection());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void eliminarPrerrequisito(Paa paa, int asignatura, String userdb) {
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);
		JdbcTemplate jdbcTemplate = jdbc.getJdbcTemplate();
		
		String sql = "DELETE From prerrequisito where pla_codigo = ? and asi_codigo = ? and asi_requisito = ? ";
		try {
			jdbcTemplate.update(new PreparedStatementCreator() {

				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement pstm = con.prepareStatement(sql);
					pstm.setInt(1, paa.getPlan());
					pstm.setInt(2, paa.getAsignatura().getCodigo());
					pstm.setInt(3, asignatura);

					return pstm;
				}
				
			});
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				cerrarConexion(dataSource.getConnection());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
private void cerrarConexion(Connection con) {
		if(con == null) return;
		
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

@Override
public boolean validarEquivalencia(int pla_codigo, int asi_codigo, int asi_equivalencia) {
	String sql = "select  COUNT ( equ_codigo) from equivalencia where pla_codigo = ? and asi_codigo = ? and asi_equivalencia = ? ";
	int result =  jdbcTemplate.queryForObject(sql, new Object[] { pla_codigo, asi_codigo, asi_equivalencia }, Integer.class);
	return result > 0 ? true : false;
}

@Override
public boolean validarPrerrequisito(int pla_codigo, int asi_codigo, int asi_prerrequisito) {
	String sql = "select COUNT ( pre_codigo) from prerrequisito where pla_codigo = ? and asi_codigo = ? and asi_requisito = ? ";
	int result =  jdbcTemplate.queryForObject(sql, new Object[] { pla_codigo, asi_codigo, asi_prerrequisito }, Integer.class);
	return result > 0 ? true : false;
}

@Override
public boolean validarPrerrequisitoencredito(int pla_codigo, int asi_codigo, int creditos) {
	String sql = "select COUNT ( pre_codigo) from prerrequisito where pla_codigo = ? and asi_codigo = ? and pre_creditos = ? ";
	int result =  jdbcTemplate.queryForObject(sql, new Object[] { pla_codigo, asi_codigo, creditos }, Integer.class);
	return result > 0 ? true : false;
}

@Override
public void eliminarPrerrequisitoenCredito(Paa paa, int creditos, String userdb) {
	DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
	NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);
	JdbcTemplate jdbcTemplate = jdbc.getJdbcTemplate();
	
	String sql = "DELETE From prerrequisito where pla_codigo = ? and asi_codigo = ? and pre_creditos = ? ";
	try {
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstm = con.prepareStatement(sql);
				pstm.setInt(1, paa.getPlan());
				pstm.setInt(2, paa.getAsignatura().getCodigo());
				pstm.setInt(3, creditos);

				return pstm;
			}
			
		});
	}catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		try {
			cerrarConexion(dataSource.getConnection());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

}
