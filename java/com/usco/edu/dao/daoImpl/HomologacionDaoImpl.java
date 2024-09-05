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
import org.springframework.stereotype.Repository;

import com.usco.edu.dao.IHomologacionDao;
import com.usco.edu.entities.Homologacion;
import com.usco.edu.entities.HomologacionTipo;
import com.usco.edu.entities.SimulacionHomo;
import com.usco.edu.entities.Traslado;
import com.usco.edu.resultSetExtractor.HomoTipoSetExtractor;
import com.usco.edu.resultSetExtractor.SimulacionSetExtrartor;
import com.usco.edu.resultSetExtractor.TrasladoSetExtractor;
import com.usco.edu.util.AuditoriaJdbcTemplate;

@Repository
public class HomologacionDaoImpl implements IHomologacionDao{
	
	@Autowired
	private AuditoriaJdbcTemplate jdbcComponent;
	
	@Autowired
	@Qualifier("JDBCTemplatePlanesConsulta")
	public JdbcTemplate jdbcTemplate;

	@Override
	public List<SimulacionHomo> SimularHomologaciones(String userdb, String est_codigo, int pla_old, int pla_new) {

		
		String sql = "	select h.paa_codigo_viejo, h.paa_codigo_nuevo, h.hot_codigo, h.hom_grupo, " + 
				"	vista.mat_codigo, vista.paa_codigo, vista.mac_definitiva, vista.mat_codigo, vista.est_codigo, vista.paa_nota_minima, vista.uaa, " + 
				"	plaold.pla_nombre as pla_old, planew.pla_nombre as pla_new, " + 
				"	asiold.asi_nombre as nom_old, asinew.asi_nombre as nom_new, planew.pro_codigo pro_new, pro_new.uaa_nombre_corto uaa_new, vista.cur_codigo, vista.mac_subgrupo " + 
				"	from homologada h " + 
				"	left join  " + 
				"	(select mc.paa_codigo, mc.mac_definitiva, m.mat_codigo, m.est_codigo, paa.paa_nota_minima, uaa_nombre_corto as uaa, mc.cur_codigo , mc.mac_subgrupo " + 
				"	from matricula_curso mc " + 
				"	left join matricula m  on mc.mat_codigo = m.mat_codigo " + 
				"	left join estudiante e 	on m.est_codigo  = e.est_codigo " + 
				"	left join plan_estudiante pe on e.est_codigo  = pe.est_codigo and pe.ple_estado = 1 " + 
				"	left join plan_academico pa  on pe.pla_codigo = pa.pla_codigo " + 
				"	left join plan_academico_asignatura paa on pa.pla_codigo = paa.pla_codigo and mc.paa_codigo = paa.paa_codigo " + 
				"	left join programa pro  on pa.pro_codigo = pro.pro_codigo " + 
				"	left join uaa on pro.uaa_codigo = uaa.uaa_codigo " + 
				"	where m.est_codigo = ? " + 
				"	and mc.mac_definitiva >= paa.paa_nota_minima " + 
				"	) " + 
				"	as vista on vista.paa_codigo = h.paa_codigo_viejo " + 
				"	left join plan_academico_asignatura as paaold on h.paa_codigo_viejo = paaold.paa_codigo " + 
				"	left join plan_academico_asignatura as paanew on h.paa_codigo_nuevo = paanew.paa_codigo " + 
				"	left join asignatura as asiold on paaold.asi_codigo = asiold.asi_codigo " + 
				"	left join asignatura as asinew on paanew.asi_codigo = asinew.asi_codigo " + 
				"	left join plan_academico as plaold on paaold.pla_codigo = plaold.pla_codigo " + 
				"	left join plan_academico as planew on paanew.pla_codigo = planew.pla_codigo " + 
				"	left join r_programa as pro_new on planew.pro_codigo = pro_new.pro_codigo " + 
				"	where hot_codigo not in (6,7) and hom_estado = 1 and not vista.mac_definitiva is null  " + 
				"	      and h.pla_codigo_viejo = ? and h.pla_codigo_nuevo = ?  and h.paa_codigo_nuevo in ( select te.paa_codigo_nuevo paan from " + 
				"		(select count(paa_codigo_viejo) n_asi_mat, paa_codigo_nuevo, hot_codigo, hom_grupo  " + 
				"		from matricula_curso mc inner join matricula m on mc.mat_codigo = m.mat_codigo  " + 
				"		inner join estudiante e on m.est_codigo  = e.est_codigo  " + 
				"		inner join plan_estudiante pe on e.est_codigo  = pe.est_codigo and pe.ple_estado = 1  " + 
				"		inner join plan_academico pa  on pe.pla_codigo = pa.pla_codigo   " + 
				"		inner join plan_academico_asignatura paa on pa.pla_codigo = paa.pla_codigo and mc.paa_codigo = paa.paa_codigo " + 
				"		inner join asignatura a on a.asi_codigo = paa.asi_codigo " + 
				"		inner join homologada h on h.pla_codigo_viejo=pe.pla_codigo and pla_codigo_viejo = ? and pla_codigo_nuevo = ? and " + 
				"					 paa_codigo_viejo = paa.paa_codigo and  hot_codigo is not null " + 
				"		where m.est_codigo = ? and mc.mac_definitiva >= paa.paa_nota_minima and maa_codigo in (1,2) " + 
				"		group by paa_codigo_nuevo, hot_codigo, hom_grupo  ) as te inner join " + 
				"		(	select count(paa_codigo_viejo) n_asi_hom, paa_codigo_nuevo, hot_codigo, hom_grupo  " + 
				"			from homologada " + 
				"			where pla_codigo_viejo = ? and pla_codigo_nuevo = ? and hot_codigo is not null " + 
				"			group by paa_codigo_nuevo, hot_codigo, hom_grupo  " + 
				"		) as th on te.paa_codigo_nuevo = th.paa_codigo_nuevo and te.n_asi_mat = th.n_asi_hom ) " + 
				"	order by paaold.paa_semestre, asiold.asi_nombre, asinew.asi_nombre ";
		List<SimulacionHomo> simulacion = null;
		try {
			simulacion = jdbcTemplate.query(sql,new Object[] {est_codigo ,pla_old , pla_new, pla_old ,pla_new ,est_codigo ,pla_old ,pla_new },
					new SimulacionSetExtrartor());
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return simulacion;
	}

	@Override
	public boolean insertPlanEstudiante(String userdb, String est_codigo, int pla_new, String Observacion) {
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);
		JdbcTemplate jdbcTemplate = jdbc.getJdbcTemplate();
		String sql = "INSERT INTO plan_estudiante (est_codigo,pla_codigo,ple_estado ,ple_observacion ) values ( ? , ? , ? , ?) " ;
		
		int result = 0;
		try {
			  result =jdbcTemplate.update(new PreparedStatementCreator() {

					@Override
					public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
						PreparedStatement pstm = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
						pstm.setString(1, est_codigo);
						pstm.setInt(2, pla_new);
						pstm.setInt(3, 1);
						pstm.setString(4, Observacion);
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
	public boolean desactivarPlanEstudiante(String userdb, String est_codigo, int pla_old) {
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);
		JdbcTemplate jdbcTemplate = jdbc.getJdbcTemplate();
		String sql = "update plan_estudiante set ple_estado = ? where est_codigo  = ? and pla_codigo = ? " ;
		
		int result = 0;
		try {
			  result =jdbcTemplate.update(new PreparedStatementCreator() {

					@Override
					public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
						PreparedStatement pstm = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
						pstm.setInt(1, 0);
						pstm.setString(2, est_codigo);
						pstm.setInt(3, pla_old);
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
	public boolean realizarHomologacionesEnMatriculaCurso(String userdb, String observacion,
			SimulacionHomo homolohacion) {
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);
		JdbcTemplate jdbcTemplate = jdbc.getJdbcTemplate();
		
		String sql = "insert into matricula_curso_actual (mat_codigo, mac_subgrupo, mac_estado, mac_nota, mac_definitiva, " + 
				"maf_codigo, paa_codigo, mac_novedad, maa_codigo, cur_codigo , mac_cliente ) " + 
				"values ( ? , ? , ?, ?, ?, ?, ?, ?, ?, ? , ?) " ;
		
		int result = 0;
		try {
			  result =jdbcTemplate.update(new PreparedStatementCreator() {

					@Override
					public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
						PreparedStatement pstm = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
						pstm.setLong(1, homolohacion.getMatricula());
						pstm.setString(2, homolohacion.getSubgrupo());
						pstm.setInt(3, 1);
						pstm.setDouble(4, homolohacion.getNota());
						pstm.setDouble(5, homolohacion.getNota());
						pstm.setInt(6, 6);
						pstm.setLong(7, homolohacion.getCodigoPaanew());
						pstm.setString(8, observacion);
						pstm.setInt(9, 2);
						pstm.setString(10, homolohacion.getCurso());
						pstm.setString(11, "PLANES_ACADEMICOS_REGISTRO-Y-CONTROL_V-2");
						
						
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
	public boolean validarPlanNew(String userdb, String est_codigo, int pla_new) {
	
		String sql = "select count(est_codigo) from plan_estudiante where est_codigo  = ? and pla_codigo = ? " ;
		int valor = 0;
		try {
			valor = jdbcTemplate.queryForObject(sql, new Object[] { est_codigo, pla_new }, Integer.class);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return  valor > 0 ? true : false;
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
	public List<HomologacionTipo> Homotipoall(String userdb) {

		String sql = "select * from homologacion_tipo where hot_codigo in (1,2,3,4,5) ";
		List<HomologacionTipo> homologacionTipo =null ;
		try {
			homologacionTipo = jdbcTemplate.query(sql, new HomoTipoSetExtractor());
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return homologacionTipo;
	}

	@Override
	public boolean actualizarProgramaenEstudiante(String userdb, String est_codigo, long pro_codigo) {
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);
		JdbcTemplate jdbcTemplate = jdbc.getJdbcTemplate();
		String sql = "update estudiante set pro_codigo = ? where est_codigo = ? " ;
		
		int result = 0;
		try {
			  result =jdbcTemplate.update(new PreparedStatementCreator() {

					@Override
					public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
						PreparedStatement pstm = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
						pstm.setLong(1, pro_codigo);
						pstm.setString(2, est_codigo);
	
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
	public boolean validarPaaRepetida(String userdb, SimulacionHomo homolohacion) {

		String sql = "select COUNT(mac_codigo) from matricula_curso where mat_codigo = ? and paa_codigo = ? " ;
		int valor = 0;
		try {
			valor = jdbcTemplate.queryForObject(sql, new Object[] { homolohacion.getMatricula(), homolohacion.getCodigoPaanew()}, Integer.class);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return  valor > 0 ? true : false;
		
	}

	@Override
	public boolean validarHomologacion(String userdb, Homologacion homologacion) {

		String sql = "select COUNT(hom_codigo) from homologada where paa_codigo_viejo = ? and "
				+ "paa_codigo_nuevo = ? and  pla_codigo_viejo = ? and  pla_codigo_nuevo = ?  and hot_codigo not in (7,6)" ;
		int valor = 0;
		try {
			valor = jdbcTemplate.queryForObject(sql, new Object[] { homologacion.getCodigoPaaold() ,homologacion.getCodigoPaanew(),
					homologacion.getPlanold() , homologacion.getPlannew()}, Integer.class);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return  valor > 0 ? true : false;
	}

	@Override
	public boolean activarHomologacion(String userdb, Homologacion homologacion) {
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);
		JdbcTemplate jdbcTemplate = jdbc.getJdbcTemplate();
		String sql = "update homologada set hom_estado = 1 where paa_codigo_viejo = ? and paa_codigo_nuevo = ? "
				+ " and pla_codigo_viejo = ? and  pla_codigo_nuevo = ? " ;
		
		int result = 0;
		try {
			  result =jdbcTemplate.update(new PreparedStatementCreator() {

					@Override
					public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
						PreparedStatement pstm = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
						pstm.setLong(1, homologacion.getCodigoPaaold());
						pstm.setLong(2, homologacion.getCodigoPaanew());
						pstm.setInt(3, homologacion.getPlanold());
						pstm.setInt(4, homologacion.getPlannew());
	
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
	public boolean crearHomologacion(String userdb, Homologacion homologacion) {
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);
		JdbcTemplate jdbcTemplate = jdbc.getJdbcTemplate();
		String sql = "INSERT INTO homologada (paa_codigo_viejo, paa_codigo_nuevo, pla_codigo_viejo , pla_codigo_nuevo, "
				+ " hot_codigo, hom_grupo, hom_estado ) values ( ? , ? , ? , ? , ? , ? , ? ) " ;
		
		int result = 0;
		try {
			  result =jdbcTemplate.update(new PreparedStatementCreator() {

					@Override
					public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
						PreparedStatement pstm = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
						pstm.setLong(1, homologacion.getCodigoPaaold());
						pstm.setLong(2, homologacion.getCodigoPaanew());
						pstm.setInt(3, homologacion.getPlanold());
						pstm.setInt(4, homologacion.getPlannew());
						pstm.setInt(5, homologacion.getHomologacionTipo().getCodigo());
						pstm.setInt(6, homologacion.getGrupo());
						pstm.setInt(7, 1);
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
	public boolean borrarHomologacion(String userdb, Homologacion homologacion) {
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);
		JdbcTemplate jdbcTemplate = jdbc.getJdbcTemplate();
		String sql = "update homologada set hom_estado = 0 where paa_codigo_viejo = ? and paa_codigo_nuevo = ? "
				+ " and hom_codigo = ?" ;
		
		int result = 0;
		try {
			  result =jdbcTemplate.update(new PreparedStatementCreator() {

					@Override
					public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
						PreparedStatement pstm = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
						pstm.setLong(1, homologacion.getCodigoPaaold());
						pstm.setLong(2, homologacion.getCodigoPaanew());
						pstm.setLong(3, homologacion.getCodigo());

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
	public List<SimulacionHomo> SimularHomologacionesSinEst_codigo(String userdb, Homologacion homologacion) {
	
		
		String sql = " select h.paa_codigo_viejo,  cast(a_old.asi_codigo as varchar) + ' - ' + a_old.asi_nombre_corto  + ' - ' + a_old.asi_nombre as nom_old, " + 
				"	h.paa_codigo_nuevo, cast(a_new.asi_codigo as varchar) + ' - ' +  a_new.asi_nombre_corto  + ' - ' + a_new.asi_nombre as nom_new, " + 
				"	h.hot_codigo, ht.hot_nombre , h.hom_codigo " + 
				"	from homologada h " + 
				"	left join plan_academico_asignatura paa_old on h.paa_codigo_viejo = paa_old.paa_codigo " + 
				"	left join plan_academico_asignatura paa_new on h.paa_codigo_nuevo = paa_new.paa_codigo " + 
				"	left join asignatura a_old on paa_old.asi_codigo = a_old.asi_codigo " + 
				"	left join asignatura a_new on paa_new.asi_codigo = a_new.asi_codigo " + 
				"	left join plan_academico as p_old on h.pla_codigo_viejo = p_old.pla_codigo " + 
				"	left join plan_academico as p_new on h.pla_codigo_nuevo = p_new.pla_codigo " + 
				"	left join programa as pro on p_old.pro_codigo = pro.pro_codigo " + 
				"	left join uaa on pro.uaa_codigo = uaa.uaa_codigo " + 
				"	left join r_programa as pro_new on p_new.pro_codigo = pro_new.pro_codigo "+
				"	left join homologacion_tipo ht on ht.hot_codigo = h.hot_codigo " +  
				"	where not h.hot_codigo is null  " + 
				"	and h.pla_codigo_viejo = ? " + 
				"	and h.pla_codigo_nuevo = ? " + 
				"	and hom_estado = 1 and h.hot_codigo not in (7,6)" + 
				"	order by paa_old.paa_semestre, a_old.asi_nombre, a_new.asi_nombre ";
		List<SimulacionHomo> simulacion = null;
		try {
			simulacion = jdbcTemplate.query(sql,new Object[] {homologacion.getPlanold() ,homologacion.getPlannew()},
					new SimulacionSetExtrartor());
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return simulacion;
	}

	@Override
	public String obtenergrupoHomo(String userdb, Homologacion homologacion) {

		String sql = "select MAX(hom_grupo) from homologada where  pla_codigo_viejo = ? " + 
				"	and pla_codigo_nuevo = ? and hot_codigo not in (7,6) " ;
		String valor = null;
		try {
			valor = jdbcTemplate.queryForObject(sql, new Object[] { homologacion.getPlanold() , homologacion.getPlannew()}, String.class);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return valor;
	}

	@Override
	public List<SimulacionHomo> SimularTrasladoAsignaturas(String userdb, String est_codigo, int pla_old, int pla_new) {

		String sql = "	select h.paa_codigo_viejo, h.paa_codigo_nuevo, h.hot_codigo, h.hom_grupo, " + 
				"	vista.mat_codigo, vista.paa_codigo, vista.mac_definitiva, vista.mat_codigo, vista.est_codigo, vista.paa_nota_minima, vista.uaa, " + 
				"	plaold.pla_nombre as pla_old, planew.pla_nombre as pla_new, " + 
				"	asiold.asi_nombre as nom_old, asinew.asi_nombre as nom_new, planew.pro_codigo pro_new, pro_new.uaa_nombre_corto uaa_new , vista.cur_codigo , vista.mac_subgrupo " + 
				"	from homologada h " + 
				"	left join  " + 
				"	( " + 
				"	select mc.paa_codigo, mc.mac_definitiva, m.mat_codigo, m.est_codigo, paa.paa_nota_minima, uaa_nombre_corto as uaa, mc.cur_codigo ,mc.mac_subgrupo " + 
				"	from matricula_curso mc " + 
				"	left join matricula m  on mc.mat_codigo = m.mat_codigo " + 
				"	left join estudiante e 	  on m.est_codigo  = e.est_codigo " + 
				"	left join plan_estudiante pe on e.est_codigo  = pe.est_codigo and pe.ple_estado = 1 " + 
				"	left join plan_academico pa  on pe.pla_codigo = pa.pla_codigo " + 
				"	left join plan_academico_asignatura paa on pa.pla_codigo = paa.pla_codigo and mc.paa_codigo = paa.paa_codigo " + 
				"	left join programa pro       on pa.pro_codigo = pro.pro_codigo " + 
				"	left join uaa		     on pro.uaa_codigo = uaa.uaa_codigo " + 
				"	where m.est_codigo = ? " + 
				"	and mc.mac_definitiva >= paa.paa_nota_minima " + 
				"	)  " + 
				"	as vista on vista.paa_codigo = h.paa_codigo_viejo " + 
				"	left join plan_academico_asignatura as paaold on h.paa_codigo_viejo = paaold.paa_codigo " + 
				"	left join plan_academico_asignatura as paanew on h.paa_codigo_nuevo = paanew.paa_codigo " + 
				"	left join asignatura as asiold on paaold.asi_codigo = asiold.asi_codigo " + 
				"	left join asignatura as asinew on paanew.asi_codigo = asinew.asi_codigo " + 
				"	left join plan_academico as plaold on paaold.pla_codigo = plaold.pla_codigo " + 
				"	left join plan_academico as planew on paanew.pla_codigo = planew.pla_codigo " + 
				"	left join r_programa as pro_new on planew.pro_codigo = pro_new.pro_codigo " + 
				"	where not hot_codigo is null and not vista.mac_definitiva is null " + 
				"	      and h.pla_codigo_viejo = ? and h.pla_codigo_nuevo = ?  and h.hot_codigo in (7,6) and h.paa_codigo_nuevo in ( select te.paa_codigo_nuevo paan from " + 
				"		(select count(paa_codigo_viejo) n_asi_mat, paa_codigo_nuevo, hot_codigo, hom_grupo  " + 
				"		from matricula_curso mc inner join matricula m on mc.mat_codigo = m.mat_codigo  " + 
				"		inner join estudiante e on m.est_codigo  = e.est_codigo  " + 
				"		inner join plan_estudiante pe on e.est_codigo  = pe.est_codigo and pe.ple_estado = 1  " + 
				"		inner join plan_academico pa  on pe.pla_codigo = pa.pla_codigo   " + 
				"		inner join plan_academico_asignatura paa on pa.pla_codigo = paa.pla_codigo and mc.paa_codigo = paa.paa_codigo  " + 
				"		inner join asignatura a on a.asi_codigo = paa.asi_codigo " + 
				"		inner join homologada h on h.pla_codigo_viejo=pe.pla_codigo and pla_codigo_viejo = ? and pla_codigo_nuevo = ?  and " + 
				"					 paa_codigo_viejo = paa.paa_codigo and  hot_codigo is not null " + 
				"		where m.est_codigo = ? and mc.mac_definitiva >= paa.paa_nota_minima and maa_codigo in (1,2)  " + 
				"		group by paa_codigo_nuevo, hot_codigo, hom_grupo  ) as te inner join " + 
				"		(	select count(paa_codigo_viejo) n_asi_hom, paa_codigo_nuevo, hot_codigo, hom_grupo  " + 
				"			from homologada " + 
				"			where pla_codigo_viejo = ? and pla_codigo_nuevo = ? and hot_codigo is not null " + 
				"			group by paa_codigo_nuevo, hot_codigo, hom_grupo  " + 
				"		) as th on te.paa_codigo_nuevo = th.paa_codigo_nuevo and te.n_asi_mat = th.n_asi_hom ) " + 
				"	order by paaold.paa_semestre, asiold.asi_nombre, asinew.asi_nombre ";
		List<SimulacionHomo> simulacion = null;
		try {
			simulacion = jdbcTemplate.query(sql,new Object[] {est_codigo ,pla_old , pla_new, pla_old ,pla_new ,est_codigo ,pla_old ,pla_new },
					new SimulacionSetExtrartor());
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return simulacion;
	}

	@Override
	public List<Traslado> AsignaturasforTraslado(String userdb, Homologacion homologacion) {
		
		String sql = " 	select vista.*, hot_nombre  from ( " + 
				"		select t1.asi_nombre_corto+ ' - '+ t1.asi_nombre nom_old, t1.paa_semestre s_old, paa_old,  " + 
				"		 t2.asi_nombre_corto+' - '+ t2.asi_nombre nom_new, t2.paa_semestre s_new, paa_new,   " + 
				"		t1.pla_nombre p_old, t2.pla_nombre p_new, t1.pla_codigo pla_old, t2.pla_codigo pla_new,   " + 
				"		'aplica' = case 	 " + 
				"		when  t1.asi_nombre IS not null and t2.asi_nombre IS not null then 'si'  " + 
				"		else 'no' end  " + 
				"		from  (   " + 
				"			select pa.pla_codigo, pla_nombre, paa.asi_codigo, paa_semestre, a.asi_nombre, a.asi_nombre_corto, paa.paa_codigo paa_old    " + 
				"			from plan_academico pa, plan_academico_asignatura paa, asignatura a   " + 
				"			where pa.pla_codigo = paa.pla_codigo and pa.pla_codigo = ? and a.asi_codigo = paa.asi_codigo ) as t1   " + 
				"		full join (  " + 
				"			select pa.pla_codigo, pla_nombre, paa.asi_codigo, paa_semestre, a.asi_nombre, a.asi_nombre_corto, paa.paa_codigo paa_new   " + 
				"			from plan_academico pa, plan_academico_asignatura paa, asignatura a    " + 
				"			where pa.pla_codigo = paa.pla_codigo and pa.pla_codigo = ? and a.asi_codigo = paa.asi_codigo ) as t2  " + 
				"		on t1.asi_codigo = t2.asi_codigo  " + 
				"		) as vista left join homologada h on " + 
				"		h.paa_codigo_viejo = vista.paa_old " + 
				"		and h.paa_codigo_nuevo = vista.paa_new " + 
				"		and h.pla_codigo_viejo = vista.pla_old  " + 
				"		and h.pla_codigo_nuevo = vista.pla_new and hot_codigo in (6,7) " + 
				"		left join homologacion_tipo ht on ht.hot_codigo = h.hot_codigo " + 
				"		order by aplica desc, s_old, nom_old, s_new, nom_new ";
		List<Traslado> simulacion = null;
		try {
			simulacion = jdbcTemplate.query(sql,new Object[] {homologacion.getPlanold() ,homologacion.getPlannew()},
					new TrasladoSetExtractor());
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return simulacion;
	}

	@Override
	public boolean crearTraslado(String userdb, Traslado traslado) {
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);
		JdbcTemplate jdbcTemplate = jdbc.getJdbcTemplate();
		String sql = "INSERT INTO homologada (paa_codigo_viejo, paa_codigo_nuevo, pla_codigo_viejo , pla_codigo_nuevo, "
				+ " hot_codigo, hom_grupo, hom_estado ) values ( ? , ? , ? , ? , ? , ? , ? ) " ;
		
		int result = 0;
		try {
			  result =jdbcTemplate.update(new PreparedStatementCreator() {

					@Override
					public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
						PreparedStatement pstm = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
						pstm.setString(1, traslado.getCodigoPaaold());
						pstm.setString(2, traslado.getCodigoPaanew());
						pstm.setString(3, traslado.getPlanold());
						pstm.setString(4, traslado.getPlannew());
						pstm.setInt(5, traslado.getHot_codigo());
						pstm.setInt(6, traslado.getGrupo());
						pstm.setInt(7, 1);
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
	public boolean validarTraslado(String userdb, Traslado tralado) {

		String sql = "select COUNT(hom_codigo) from homologada where paa_codigo_viejo = ? and "
				+ "paa_codigo_nuevo = ? and  pla_codigo_viejo = ? and  pla_codigo_nuevo = ?  and hot_codigo in (7,6)" ;
		int valor = 0;
		try {
			valor = jdbcTemplate.queryForObject(sql, new Object[] { tralado.getCodigoPaaold() ,tralado.getCodigoPaanew(),
					tralado.getPlanold() , tralado.getPlannew()}, Integer.class);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return  valor > 0 ? true : false;
	}

	@Override
	public String obtenergrupoTraslado(String userdb, Traslado tralado) {

		String sql = "select MAX(hom_grupo) from homologada where  pla_codigo_viejo = ? " + 
				"	and pla_codigo_nuevo = ? and hot_codigo in (7,6) " ;
		String valor = null;
		try {
			valor = jdbcTemplate.queryForObject(sql, new Object[] { tralado.getPlanold() , tralado.getPlannew()}, String.class);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return valor;
	}

}
