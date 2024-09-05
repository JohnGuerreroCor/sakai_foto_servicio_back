package com.usco.edu.dao.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.dao.ILoginDao;

@Repository
public class LoginDaoImpl implements ILoginDao{
	
	@Autowired
	@Qualifier("JDBCTemplatePlanesLogin")
	public JdbcTemplate jdbcTemplate;

	@Override
	public String obtenerSegundaClaveReal( String segundaClave) {
		String sql ="SELECT dbo.encriptarClaveReal(?) as clave";
		String claveReal = jdbcTemplate.queryForObject(sql, new Object[] {segundaClave }, new RowMapper<String>() {

			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString("clave");
			}
			
		});
		 return claveReal;
	}
	
	

}
