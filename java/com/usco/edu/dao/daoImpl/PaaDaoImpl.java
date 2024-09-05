package com.usco.edu.dao.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.usco.edu.dao.IPaaDao;
import com.usco.edu.entities.Componente;
import com.usco.edu.entities.Paa;
import com.usco.edu.resultSetExtractor.ComponenteSetExtractor;
import com.usco.edu.resultSetExtractor.PaaSetExtractor;
import com.usco.edu.rowMapper.PaaRowMapper;
import com.usco.edu.util.AuditoriaJdbcTemplate;
import com.usco.edu.util.Conexion;


@Repository
public class PaaDaoImpl implements IPaaDao{
	@Autowired
	private AuditoriaJdbcTemplate jdbcComponent;
	
	@Autowired
	@Qualifier("JDBCTemplatePlanesConsulta")
	public JdbcTemplate jdbcTemplate;

	@Override
	public List<Paa> findbyIdPlan(int pla_codigo, String userdb) {

		String sql = "SELECT * FROM plan_academico_asignatura paa " 
				+ "inner join asignatura asi on asi.asi_codigo = paa.asi_codigo " 
				+ "inner join uaa ua on ua.uaa_codigo = asi.uaa_codigo " 
			//	+ "inner join uaa_tipo ut on ut.uat_codigo = ua.uat_codigo "
				+ "inner join sede se on se.sed_codigo = ua.sed_codigo "
				+ "left join componente com on com.com_codigo = paa.com_codigo " 
				+ "WHERE paa.pla_codigo = ? order by paa.paa_semestre asc";
		List<Paa> paas = null;
		try {
			 paas = jdbcTemplate.query(sql, new Object[] {pla_codigo}, new PaaSetExtractor());
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return paas;
	}

	@Override
	public Paa findById(int codigo , String userdb) {
	//	NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplate(userdb);
		
		String sql = "SELECT * FROM plan_academico_asignatura paa " 
				+ "inner join asignatura asi on asi.asi_codigo = paa.asi_codigo " 
				+ "inner join uaa ua on ua.uaa_codigo = asi.uaa_codigo " 
			//	+ "inner join uaa_tipo ut on ut.uat_codigo = ua.uat_codigo "
				+ "inner join sede se on se.sed_codigo = ua.sed_codigo " 
				+ "left join componente com on com.com_codigo = paa.com_codigo "
				+ "WHERE paa.paa_codigo = ? ";
		Paa paa = null;
		try {
			paa = jdbcTemplate.queryForObject(sql, new Object[] { codigo }, new PaaRowMapper());
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return paa;
	}

	@Override
	public List<Componente> allComponent(String userdb) {
		//NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplate(userdb);

		
		String sql = "SELECT * FROM componente";
		List<Componente>  componentes = null;
		try {
			componentes = jdbcTemplate.query(sql, new ComponenteSetExtractor());
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return componentes;
	}

	@Override
	public void updatePaa(Paa paa, String userdb) {
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);
		JdbcTemplate jdbcTemplate = jdbc.getJdbcTemplate();
		
	String sql = "UPDATE plan_academico_asignatura Set asi_codigo = ? , paa_semestre = ? ,paa_credito = ? , "
		   + "paa_intensidad = ? ,paa_estado = ? ,paa_nota_minima = ? , paa_chequeo_requisito = ? " 
		   + ",paa_plan_academico = ? , paa_obligatoria = ? ,paa_programable = ? , paa_multi_asignatura = ? " 
		   + ",paa_semanaxsemestre = ? , paa_h_trabajo_ind_sem = ? , com_codigo = ? WHERE paa_codigo = ? ";
	try {
		 jdbcTemplate.update(new PreparedStatementCreator() {

				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement pstm = con.prepareStatement(sql);
					pstm.setInt(1, paa.getAsignatura().getCodigo());
					pstm.setInt(2, paa.getSemestre());
					pstm.setInt(3, paa.getCreditos());
					pstm.setString(4, paa.getIntensidad());
					pstm.setInt(5, paa.getEstado());
					pstm.setDouble(6, paa.getNotaMinima());
					pstm.setString(7, paa.getChequeoRequisito());
					pstm.setString(8, paa.getPlanAcademico());
					pstm.setString(9, paa.getPlanAcademico());
					pstm.setString(10, paa.getProgramable());
					pstm.setString(11, paa.getMultiAsignatura());
					pstm.setString(12, paa.getSemanaxsemestre());
					pstm.setString(13, paa.getHtrabajoSemestre());
					pstm.setInt(14,paa.getComponente().getCodigo());
					pstm.setInt(15,paa.getCodigo());
					
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
	public void crearPaa(Paa paa, String userdb) {
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		//NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);
		//JdbcTemplate jdbcTemplate = jdbc.getJdbcTemplate();
		
		Connection conexion = null;
		PreparedStatement sentencia = null;
		/*

		String sql = "INSERT INTO plan_academico_asignatura (pla_codigo, asi_codigo, paa_semestre, paa_credito, "
				+ "paa_intensidad, paa_estado, paa_nota_minima, paa_chequeo_requisito, "
				+ "paa_plan_academico, paa_obligatoria, paa_programable , paa_multi_asignatura, "
				+ "paa_semanaxsemestre, paa_h_trabajo_ind_sem, com_codigo)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
			
		try {
			jdbcTemplate.update(new PreparedStatementCreator() {

				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement pstm = con.prepareStatement(sql);
					pstm.setInt(1, paa.getPlan());
					pstm.setInt(2, paa.getAsignatura().getCodigo());
					pstm.setInt(3, paa.getSemestre());
					pstm.setInt(4, paa.getCreditos());
					pstm.setString(5, paa.getIntensidad());
					pstm.setInt(6, paa.getEstado());
					pstm.setFloat(7, (float) paa.getNotaMinima());
					pstm.setString(8, paa.getChequeoRequisito());
					pstm.setString(9, paa.getPlanAcademico());
					pstm.setString(10, paa.getPlanAcademico());
					pstm.setString(11, paa.getProgramable());
					pstm.setString(12, paa.getMultiAsignatura());
					pstm.setString(13, paa.getSemanaxsemestre());
					pstm.setString(14, paa.getHtrabajoSemestre());
					pstm.setInt(15, paa.getComponente().getCodigo());
					
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
		*/
		
		
		try {
			
			conexion = dataSource.getConnection();
			conexion.setAutoCommit(false);
			
			final String sql = "INSERT INTO plan_academico_asignatura (pla_codigo, asi_codigo, paa_semestre, paa_credito, " + 
					"paa_intensidad, paa_estado, paa_nota_minima, paa_chequeo_requisito, " + 
					"paa_plan_academico, paa_obligatoria, paa_programable , paa_multi_asignatura,  " + 
					"paa_semanaxsemestre, paa_h_trabajo_ind_sem, com_codigo) " + 
					" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
			sentencia = conexion.prepareStatement(sql);
			
			sentencia.setInt(1, paa.getPlan());
			sentencia.setInt(2, paa.getAsignatura().getCodigo());
			sentencia.setInt(3, paa.getSemestre());
			sentencia.setInt(4, paa.getCreditos());
			sentencia.setString(5, paa.getIntensidad());
			sentencia.setInt(6, paa.getEstado());
			sentencia.setFloat(7, (float) paa.getNotaMinima());
			sentencia.setString(8, paa.getChequeoRequisito());
			sentencia.setString(9, paa.getPlanAcademico());
			sentencia.setString(10, paa.getPlanAcademico());
			sentencia.setString(11, paa.getProgramable());
			sentencia.setString(12, paa.getMultiAsignatura());
			sentencia.setString(13, paa.getSemanaxsemestre());
			sentencia.setString(14, paa.getHtrabajoSemestre());
			sentencia.setInt(15, paa.getComponente().getCodigo());
			
			sentencia.executeUpdate();
			conexion.commit();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			System.out.println("cerrando conexion ");
			Conexion.cerrarConexion(sentencia);
			Conexion.cerrarConexion(conexion);
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
public boolean validarPaa(int pla_codigo, Paa paa) {
	int result = 0;
	String sql = "select count(paa_codigo) from plan_academico_asignatura where pla_codigo = ? and asi_codigo = ? ";
	result =  jdbcTemplate.queryForObject(sql, new Object[] { pla_codigo, paa.getAsignatura().getCodigo() }, Integer.class);
	return result > 0 ? true : false;
}


}
