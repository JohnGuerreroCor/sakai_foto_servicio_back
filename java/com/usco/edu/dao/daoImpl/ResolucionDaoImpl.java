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

import com.usco.edu.dao.IResolucionDao;
import com.usco.edu.entities.NormativaTipo;
import com.usco.edu.entities.Resolucion;
import com.usco.edu.resultSetExtractor.NormativaTipoSetExtractor;
import com.usco.edu.resultSetExtractor.ResolucionSetExtractor;
import com.usco.edu.rowMapper.ResolucionRowMapper;
import com.usco.edu.util.AuditoriaJdbcTemplate;
@Repository
public class ResolucionDaoImpl implements IResolucionDao{
	
	@Autowired
	private AuditoriaJdbcTemplate jdbcComponent;
	
	@Autowired
	@Qualifier("JDBCTemplatePlanesConsulta")
	public JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Resolucion> findAll(String userdb) {

		String sql = "select * from resolucion r " + 
				"	left join tipo_normativa tn on r.tin_codigo = tn.tin_codigo " + 
				"	order by res_codigo desc";
		List<Resolucion> resoluciones = null;
		try {
			resoluciones = jdbcTemplate.query(sql,new ResolucionSetExtractor());
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resoluciones;
	}

	@Override
	public Resolucion findbyId(String userdb, Long codigo) {

		String sql = "select * from resolucion r "
				+ "left join tipo_normativa tn on r.tin_codigo = tn.tin_codigo where res_codigo = ?";
		Resolucion resolucion = null;
		try {
			resolucion = jdbcTemplate.queryForObject(sql,new Object[] {codigo} ,new ResolucionRowMapper());
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resolucion; 	}

	@Override
	public List<Resolucion> findbyDescripcion(String userdb, String descripcion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalResolucionesAll(String userdb) {

		String sql = "select COUNT(res_codigo) from resolucion ";
		int total = 0;
		try {
			total = jdbcTemplate.queryForObject(sql, Integer.class);	
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return total;
	}

	@Override
	public int getTotalResolucinesbyDescrip(String userdb) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Resolucion createResolucion(String userdb, Resolucion resolucion) {
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);
		JdbcTemplate jdbcTemplate = jdbc.getJdbcTemplate();
		KeyHolder keyHolder = new GeneratedKeyHolder();
		

		String sql = "INSERT INTO resolucion (res_dependencia,res_descripcion,res_numero, tin_codigo , res_fecha) values (?, ?, ?, ?, ? ) ";
		int result = 0 ;
		try {
			result =  jdbcTemplate.update(new PreparedStatementCreator() {

				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement pstm = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
					pstm.setInt(1,resolucion.getDependencia());
					pstm.setString(2, resolucion.getDescripcion());
					pstm.setString(3, resolucion.getNumero());
					pstm.setLong(4,resolucion.getNormativaTipo().getCodigo());
					pstm.setTimestamp(5, new Timestamp(new Date().getTime()));
				
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
				resolucion.setCodigo(keyHolder.getKey().longValue());
			}
		return resolucion;
	}

	@Override
	public void updateResolucion(String userdb, Resolucion resolucion) {
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);
		JdbcTemplate jdbcTemplate = jdbc.getJdbcTemplate();

		String sql = "UPDATE resolucion set res_descripcion = ? , res_dependencia = ? , res_numero = ?, "
				+ "res_estado = ? , tin_codigo = ? WHERE res_codigo = ? ";
		
		try {
			 jdbcTemplate.update(new PreparedStatementCreator() {

					@Override
					public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
						PreparedStatement pstm = con.prepareStatement(sql);
						pstm.setString(1,resolucion.getDescripcion());
						pstm.setInt(2,resolucion.getDependencia());
						pstm.setString(3, resolucion.getNumero());
						pstm.setString(4, resolucion.getEstado());
						pstm.setLong(5,resolucion.getNormativaTipo().getCodigo());
						pstm.setLong(6, resolucion.getCodigo());

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
	public List<NormativaTipo> normativaTipoAll(String userdb) {

		String sql = "SELECT * FROM tipo_normativa where tin_estado = 1";
		List<NormativaTipo> normativaTipos = null;
		try {
			normativaTipos = jdbcTemplate.query(sql,new NormativaTipoSetExtractor());
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return normativaTipos;
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

}
