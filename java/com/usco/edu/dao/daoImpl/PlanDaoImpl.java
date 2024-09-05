package com.usco.edu.dao.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
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

import com.usco.edu.dao.IPlanDao;
import com.usco.edu.entities.Plan;
import com.usco.edu.resultSetExtractor.PlanSetExtractor;
import com.usco.edu.rowMapper.PlanRowMapper;
import com.usco.edu.util.AuditoriaJdbcTemplate;

@Repository
public class PlanDaoImpl implements IPlanDao {
	
	@Autowired
	private AuditoriaJdbcTemplate jdbcComponent;
	
	@Autowired
	@Qualifier("JDBCTemplatePlanesConsulta")
	public JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Plan> planesPorPrograma(int pro_codigo,String userdb) {

		
		String sql = "SELECT * FROM plan_academico pa "
				+"inner join programa p on p.pro_codigo = pa.pro_codigo " 
				+"left join resolucion r on r.res_codigo = pa.res_codigo "
				+"inner join uaa u on u.uaa_codigo = p.uaa_codigo "
				+"inner join uaa_tipo ut on ut.uat_codigo = u.uat_codigo "
				+"INNER JOIN programa_estado pe on pe.pre_codigo = p.pro_estado "
				+"INNER JOIN nivel_academico na on na.nia_codigo = p.nia_codigo "
				+"INNER JOIN modalidad m on m.mod_codigo = p.mod_codigo "
				+"left join tipo_normativa tn on tn.tin_codigo = r.tin_codigo " 
				+"inner join sede s on s.sed_codigo = u.sed_codigo WHERE pa.pro_codigo = ?";
		List<Plan> planes = null;
		try {
			planes = jdbcTemplate.query(sql, new Object[] {pro_codigo}, new PlanSetExtractor());
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 return planes;
	}

	@Override
	public List<Plan> findByCodigoList(int pla_codigo,String userdb) {
		
		String sql = "SELECT * FROM plan_academico pa "
				+ "inner join programa p on p.pro_codigo = pa.pro_codigo " 
				+"left join resolucion r on r.res_codigo = pa.res_codigo " 
				+"inner join uaa u on u.uaa_codigo = p.uaa_codigo "
				+"inner join uaa_tipo ut on ut.uat_codigo = u.uat_codigo "
				+"INNER JOIN programa_estado pe on pe.pre_codigo = p.pro_estado "
				+"INNER JOIN nivel_academico na on na.nia_codigo = p.nia_codigo "
				+"INNER JOIN modalidad m on m.mod_codigo = p.mod_codigo "
				+"left join tipo_normativa tn on tn.tin_codigo = r.tin_codigo "
				+"inner join sede s on s.sed_codigo = u.sed_codigo WHERE pa.pla_codigo = ?";
		List<Plan> planes = null;
		try {
			planes = jdbcTemplate.query(sql, new Object[] {pla_codigo}, new PlanSetExtractor());
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 return planes;
	}

	@Override
	public Plan findByCodigo(int pla_codigo,String userdb) {
		
		String sql = "SELECT * FROM plan_academico pa "
				+"inner join programa p on p.pro_codigo = pa.pro_codigo " 
				+"left join resolucion r on r.res_codigo = pa.res_codigo " 
				+"inner join uaa u on u.uaa_codigo = p.uaa_codigo "
				+"inner join uaa_tipo ut on ut.uat_codigo = u.uat_codigo "
				+"INNER JOIN programa_estado pe on pe.pre_codigo = p.pro_estado "
				+"INNER JOIN nivel_academico na on na.nia_codigo = p.nia_codigo "
				+"INNER JOIN modalidad m on m.mod_codigo = p.mod_codigo "
				+"left join tipo_normativa tn on tn.tin_codigo = r.tin_codigo " 
				+"inner join sede s on s.sed_codigo = u.sed_codigo WHERE pa.pla_codigo = ?";
		Plan plan  = null;
		try {
			plan = jdbcTemplate.queryForObject(sql, new Object[] { pla_codigo }, new PlanRowMapper());
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return plan;
	}

	@Override
	public Plan createPlan(Plan plan, String userdb) {
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);
		JdbcTemplate jdbcTemplate = jdbc.getJdbcTemplate();
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		String sql = "INSERT INTO plan_academico (pla_nombre, pro_codigo, pla_creditos, pla_total_creditos, "
				+"pla_tipo_registro, pla_estado , pla_fecha , res_codigo , pla_actual, " 
				+"fecha_creacion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		int result = 0;
		try {
			 result = jdbcTemplate.update(new PreparedStatementCreator() {

				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement pstm = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
					pstm.setString(1, plan.getPla_nombre());
					pstm.setFloat(2, plan.getPrograma().getCodigo());
					pstm.setInt(3, plan.getPla_creditos());
					pstm.setInt(4, plan.getCreditosTotal());
					pstm.setString(5, plan.getTipoRegistro());
					pstm.setInt(6, plan.getEstado());
					pstm.setTimestamp(7, plan.getFechaCreacion());
					pstm.setFloat(8, plan.getResolucion().getCodigo());
					pstm.setInt(9, plan.getPlan_actual());
					pstm.setTimestamp(10, plan.getFechaCreacion());
					
					return pstm;
				}
				
				
			}, keyHolder);
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
			plan.setCodigo(keyHolder.getKey().longValue());
		}
		return plan;
	}

	@Override
	public void updatePlan(Plan plan, String userdb) {
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);
		JdbcTemplate jdbcTemplate = jdbc.getJdbcTemplate();
		
		String sql = "UPDATE plan_academico set pla_nombre = ? , pla_total_creditos = ? "
				+ ",pla_creditos = ? , pla_estado = ? , pla_actual = ? ,pro_codigo = ? "
				+ ",res_codigo = ? , pla_tipo_registro = ? WHERE pla_codigo = ? ";
		
		try {
			jdbcTemplate.update(new PreparedStatementCreator() {

				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement pstm = con.prepareStatement(sql);
					pstm.setString(1, plan.getPla_nombre());
					pstm.setInt(2, plan.getCreditosTotal());
					pstm.setInt(3, plan.getPla_creditos());
					pstm.setInt(4, plan.getEstado());
					pstm.setInt(5, plan.getPlan_actual());
					pstm.setFloat(6, plan.getPrograma().getCodigo());
					pstm.setFloat(7, plan.getResolucion().getCodigo());
					pstm.setString(8, plan.getTipoRegistro());
					pstm.setFloat(9, plan.getCodigo());
					
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
	public List<Plan> planesPorUaa(int uaa_codigo, String userdb) {
		
		String sql = "	SELECT * FROM plan_academico pa " + 
				"	inner join programa p on p.pro_codigo = pa.pro_codigo " + 
				"	left join resolucion r on r.res_codigo = pa.res_codigo " + 
				"	inner join uaa u on u.uaa_codigo = p.uaa_codigo " + 
				"	inner join uaa_tipo ut on ut.uat_codigo = u.uat_codigo " + 
				"	left JOIN programa_estado pe on pe.pre_codigo = p.pro_estado " + 
				"	left JOIN nivel_academico na on na.nia_codigo = p.nia_codigo " + 
				"	left JOIN modalidad m on m.mod_codigo = p.mod_codigo " + 
				"	left join tipo_normativa tn on tn.tin_codigo = r.tin_codigo " +
				"	inner join sede s on s.sed_codigo = u.sed_codigo WHERE p.uaa_codigo = ? ";
		List<Plan> planes = null;
		try {
			planes = jdbcTemplate.query(sql, new Object[] {uaa_codigo}, new PlanSetExtractor());
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 	 return planes;
	}

	@Override
	public List<Plan> findByNombre(String nombre, String userdb) {
		
		String sql = "SELECT * FROM plan_academico pa " + 
				"INNER join programa p on p.pro_codigo = pa.pro_codigo " + 
				"INNER join uaa u on u.uaa_codigo = p.uaa_codigo " + 
				"INNER join uaa_tipo ut on ut.uat_codigo = u.uat_codigo " + 
				"INNER JOIN programa_estado pe on pe.pre_codigo = p.pro_estado " + 
				"INNER JOIN nivel_academico na on na.nia_codigo = p.nia_codigo " + 
				"INNER JOIN modalidad m on m.mod_codigo = p.mod_codigo " + 
				"left join resolucion r on r.res_codigo = pa.res_codigo " + 
				"left join tipo_normativa tn on tn.tin_codigo = r.tin_codigo " +
				"INNER join sede s on s.sed_codigo = u.sed_codigo WHERE pa.pla_nombre = ? ";
		List<Plan> planes = null;
		try {
			planes = jdbcTemplate.query(sql, new Object[] { nombre }, new PlanSetExtractor());
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return planes;
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
	public List<Plan> findByCodigotraslados(String userdb, int pla_codigo) {
		
		String sql = " SELECT * FROM plan_academico pa " + 
				"inner join programa p on p.pro_codigo = pa.pro_codigo " + 
				"left join resolucion r on r.res_codigo = pa.res_codigo " + 
				"inner join uaa u on u.uaa_codigo = p.uaa_codigo " + 
				"inner join uaa_tipo ut on ut.uat_codigo = u.uat_codigo  " + 
				"left JOIN programa_estado pe on pe.pre_codigo = p.pro_estado " + 
				"left JOIN nivel_academico na on na.nia_codigo = p.nia_codigo " + 
				"left JOIN modalidad m on m.mod_codigo = p.mod_codigo " + 
				"left join tipo_normativa tn on tn.tin_codigo = r.tin_codigo " + 
				"inner join sede s on s.sed_codigo = u.sed_codigo " + 
				"inner join homologada ho on ho.pla_codigo_nuevo = pa.pla_codigo WHERE ho.pla_codigo_viejo = ? and hot_codigo in (7,6) and hom_grupo = 1 ";
		List<Plan> planes = null;
		try {
			planes = jdbcTemplate.query(sql, new Object[] {pla_codigo}, new PlanSetExtractor());
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 return planes;
	}

}
