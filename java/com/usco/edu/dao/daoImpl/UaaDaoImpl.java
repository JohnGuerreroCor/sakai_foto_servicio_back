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

import com.usco.edu.dao.IUaaDao;
import com.usco.edu.entities.Uaa;
import com.usco.edu.entities.UaaTipo;
import com.usco.edu.resultSetExtractor.UaaSetExtractor;
import com.usco.edu.resultSetExtractor.UaaSimpleSetExtractor;
import com.usco.edu.resultSetExtractor.UaaTipoSetExtractor;
import com.usco.edu.resultSetExtractor.UaaUnificadaSetExtractor;
import com.usco.edu.rowMapper.UaaRowMapper;
import com.usco.edu.util.AuditoriaJdbcTemplate;

@Repository
public class UaaDaoImpl implements IUaaDao{
	
	@Autowired
	private AuditoriaJdbcTemplate jdbcComponent;
	
	@Autowired
	@Qualifier("JDBCTemplatePlanesConsulta")
	public JdbcTemplate jdbcTemplate;

	@Override
	public List<Uaa> allUaa(String userdb) {

		String sql = "Select * from uaa ua "
				+ "inner join sede s on s.sed_codigo = ua.sed_codigo "
				+ "inner join uaa_tipo ut on ut.uat_codigo = ua.uat_codigo "
				+ "left join municipio m on m.mun_codigo = ua.mun_codigo  where ua.uaa_estado = 1";
		List<Uaa> uaas = null;
		
		try {
			uaas = jdbcTemplate.query(sql, new UaaSetExtractor());
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return uaas;
	}

	@Override
	public List<UaaTipo> uaaTipos(String userdb) {
	//	 String sql = "select * from uaa_tipo where uat_estado = 1" ;
		String sql = "select * from uaa_tipo " ;
		 return jdbcTemplate.query(sql, new UaaTipoSetExtractor());
	}

	@Override
	public List<Uaa> findByTipoUaa(String userdb, int tipoUaa) {

		String sql = "Select * from uaa ua "+
			 "inner join sede s on s.sed_codigo = ua.sed_codigo "
		   + "inner join uaa_tipo ut on ut.uat_codigo = ua.uat_codigo "
		   + "left join municipio m on m.mun_codigo = ua.mun_codigo where ua.uat_codigo = ? and ua.uaa_estado = 1 ";
		List<Uaa> uaas = null;
		try {
			uaas = jdbcTemplate.query(sql, new Object[] { tipoUaa }, new UaaSetExtractor());
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return uaas;
	}

	@Override
	public Uaa findById(String userdb, int UaaCodigo) {

		String sql = "Select * from uaa ua "
				+ "inner join sede s on s.sed_codigo = ua.sed_codigo "
				+ "inner join uaa_tipo ut on ut.uat_codigo = ua.uat_codigo "
				+ "left join municipio m on m.mun_codigo = ua.mun_codigo where uaa_codigo = ? ";
		Uaa  uaa = null;
		try {
			uaa = jdbcTemplate.queryForObject(sql, new Object[] { UaaCodigo }, new UaaRowMapper());
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return uaa;
	}

	@Override
	public List<Uaa> findByTipoUaaPag(String userdb, int tipoUaa, int page, int records) {
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);
		JdbcTemplate jdbcTemplate = jdbc.getJdbcTemplate();
		String sql = "DECLARE @RowsPerPage INT = ?, @PageNumber INT = ? " + 
				"SELECT uaa_codigo ,uaa_estado , uaa_nombre , uaa_nombre_corto, sed_codigo ,sed_nombre " + 
				"FROM ( " + 
				"Select uaa_codigo ,uaa_estado , uaa_nombre , uaa_nombre_corto, ua.sed_codigo ,s.sed_nombre ,ROW_NUMBER() OVER (ORDER BY uaa_codigo) AS RowNum from uaa ua " + 
				"	inner join sede s on s.sed_codigo = ua.sed_codigo where uaa_estado = 1 and uat_codigo = ? ) AS OD " + 
				"WHERE OD.RowNum BETWEEN ((@PageNumber - 1 ) * @RowsPerPage) + 1 " + 
				"AND @RowsPerPage * @PageNumber ";
		
		List<Uaa> uaas = null;
		try {
			uaas = jdbcTemplate.query(sql, new Object[] { records, page, tipoUaa}, new UaaSetExtractor());
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
		
		return uaas;
	}

	@Override
	public int getTotalUaabyTipo(String userdb, int tipoUaa) {
		
		String sql = "	select count ( uaa_codigo )  from uaa ua " + 
				"inner join sede s on s.sed_codigo = ua.sed_codigo where uat_codigo = ? ";
		int total = 0;
		try {
			total = jdbcTemplate.queryForObject(sql, new Object[] { tipoUaa }, Integer.class);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return total;
	}

	@Override
	public int getTotalUaa(String userdb) {
		
		String sql = "	select count ( uaa_codigo ) from uaa  where uaa_estado = 1 ";
		int total = 0;
		try {
			total = jdbcTemplate.queryForObject(sql, new Object[] { }, Integer.class);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return total;
	}

	@Override
	public List<Uaa> findByName(String userdb, String name) {

		String sql = "Select * from uaa ua "
		   + "inner join sede s on s.sed_codigo = ua.sed_codigo "
		   + "inner join uaa_tipo ut on ut.uat_codigo = ua.uat_codigo "
		   + "left join municipio m on m.mun_codigo = ua.mun_codigo  where ua.uaa_estado = 1 and uaa_nombre like ? ";
		List<Uaa> uaas = null;
		try {
			uaas = jdbcTemplate.query(sql, new Object[] {  "%" + name + "%" }, new UaaSetExtractor());
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return uaas;
	}

	@Override
	public int getTotalUaaByName(String userdb, String name) {
		
		String sql = "select count ( uaa_codigo ) from uaa  where uaa_estado = 1 and uaa_nombre like ?";
		int total = 0;
		try {
			total = jdbcTemplate.queryForObject(sql, new Object[] {"%" + name + "%" }, Integer.class);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return total;
	}

	@Override
	public int validarIdDeLaUaa(String userdb, int UaaCodigo) {

		String sql = "Select count ( uaa_codigo ) from uaa ua "
				+ "inner join uaa_tipo ut on ut.uat_codigo = ua.uat_codigo where uaa_codigo = ? ";
		int id = 0;
		try {
			id = jdbcTemplate.queryForObject(sql, new Object[] { UaaCodigo },Integer.class);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return id;
	}

	@Override
	public Uaa newUaa(String userdb, Uaa uaa) {
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);
		JdbcTemplate jdbcTemplate = jdbc.getJdbcTemplate();
		KeyHolder keyHolder = new GeneratedKeyHolder();
		

		String sql = "INSERT INTO uaa (uaa_nombre,uaa_dependencia,uaa_nombre_corto,uaa_email,uaa_telefono,uaa_direccion, "
				+ "sed_codigo,uaa_centro_costos,uaa_acronimo,uaa_estado,uaa_jefe,uaa_ventanilla,uat_codigo, uaa_nombre_impresion)"
				+ "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
		int result = 0;
		try {
			 result =  jdbcTemplate.update(new PreparedStatementCreator() {

				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement pstm = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
					pstm.setString(1,uaa.getNombre());
					pstm.setInt(2, uaa.getUaa_dependencia());
					pstm.setString(3,uaa.getNombreCorto());
					pstm.setString(4,uaa.getEmail());
					pstm.setString(5,uaa.getTelefono());
					pstm.setString(6,uaa.getDireccion());
					pstm.setLong(7, uaa.getSede().getCodigo());
					pstm.setString(8,uaa.getCentro_costos());
					pstm.setString(9,uaa.getAcronimo());
					pstm.setInt(10,1);
					pstm.setString(11,uaa.getJefe());
					pstm.setInt(12, 604);
					pstm.setLong(13, uaa.getUaaTipo().getCodigo());
					pstm.setString(14,uaa.getNombreImpresion());
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
				uaa.setCodigo(keyHolder.getKey().longValue());
				uaa.setEstado(1);
			}
		return uaa;
	}

	@Override
	public boolean updateUaa(String userdb, Uaa uaa) {
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);
		JdbcTemplate jdbcTemplate = jdbc.getJdbcTemplate();

		String sql = "UPDATE uaa set uaa_nombre = ?, uaa_dependencia = ?, uaa_nombre_corto = ?,uaa_email = ? ,uaa_telefono = ? ,uaa_direccion = ? , " + 
					"sed_codigo = ? ,uaa_centro_costos = ? ,uaa_acronimo = ? ,uaa_estado = ? ,uaa_jefe = ? ,uat_codigo = ?, uaa_nombre_impresion = ? "
					+ " Where uaa_codigo = ? ";
		int estado = 0;
		try {
			 estado =  jdbcTemplate.update(new PreparedStatementCreator() {

				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement pstm = con.prepareStatement(sql);
					pstm.setString(1,uaa.getNombre());
					pstm.setInt(2, uaa.getUaa_dependencia());
					pstm.setString(3,uaa.getNombreCorto());
					pstm.setString(4,uaa.getEmail());
					pstm.setString(5,uaa.getTelefono());
					pstm.setString(6,uaa.getDireccion());
					pstm.setLong(7, uaa.getSede().getCodigo());
					pstm.setString(8,uaa.getCentro_costos());
					pstm.setString(9,uaa.getAcronimo());
					pstm.setInt(10,uaa.getEstado());
					pstm.setString(11,uaa.getJefe());
					pstm.setLong(12, uaa.getUaaTipo().getCodigo());
					pstm.setString(13, uaa.getNombreImpresion());
					pstm.setLong(14, uaa.getCodigo());

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
		
		return estado > 0 ? true : false;
	}

	@Override
	public List<Uaa> findBySede(String userdb, int sedeCod) {
		
		String sql = "Select * FROM uaa ua " + 
				"inner join sede s on s.sed_codigo = ua.sed_codigo Where ua.sed_codigo = ? and uat_codigo = 3  AND uaa_estado = 1 ";
		List<Uaa> uaas = null;
		try {
			uaas = jdbcTemplate.query(sql, new Object[] { sedeCod }, new UaaSimpleSetExtractor());
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return uaas;
	}

	@Override
	public List<Uaa> listUaaUnificadas(String userdb) {

		
		String sql = "Select * from uaa ua "
				+ "inner join uaa_tipo ut on ut.uat_codigo = ua.uat_codigo "
				+ " where ua.uaa_estado = 1 and ut.uat_codigo = 24 ";
		List<Uaa> uaas = null;
		try {
			uaas = jdbcTemplate.query(sql, new UaaUnificadaSetExtractor());
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return uaas;
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
	public boolean validarUaaDpto(Uaa uaa) {
		int result = 0;
		String sql = "select COUNT(uaa_codigo) from uaa where uat_codigo = 2 and uaa_nombre like ? ";
		result =  jdbcTemplate.queryForObject(sql, new Object[] { "%" + uaa.getNombre() + "%"  }, Integer.class);
		return result > 0 ? true : false;
	}

	@Override
	public void newUaaDpto(String userdb, Uaa uaa) {
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);
		JdbcTemplate jdbcTemplate = jdbc.getJdbcTemplate();
		

		String sql = "INSERT INTO uaa (uaa_nombre,uaa_dependencia,uaa_nombre_corto,uaa_email, "
				+ "sed_codigo,uaa_centro_costos,uaa_estado,uat_codigo )"
				+ "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?) ";
		try {
			  jdbcTemplate.update(new PreparedStatementCreator() {

				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement pstm = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
					pstm.setString(1,uaa.getNombre());
					pstm.setInt(2, uaa.getUaa_dependencia());
					pstm.setString(3,uaa.getNombreCorto());
					pstm.setString(4,uaa.getEmail());
					pstm.setLong(5, 1);
					pstm.setString(6,uaa.getCentro_costos());
					pstm.setInt(7,1);
					pstm.setLong(8, 2);
	
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
