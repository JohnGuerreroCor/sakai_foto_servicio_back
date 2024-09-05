package com.usco.edu.dao.daoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.usco.edu.dao.IDatosAsignaturaDao;
import com.usco.edu.entities.Caracter;
import com.usco.edu.entities.Nucleo;
import com.usco.edu.resultSetExtractor.CaracterSetExtractor;
import com.usco.edu.resultSetExtractor.NucleoSetExtractor;

@Repository
public class DatosAsignaturaDaoImpl implements IDatosAsignaturaDao{
	
	
	@Autowired
	@Qualifier("JDBCTemplatePlanesConsulta")
	public JdbcTemplate jdbcTemplate;

	@Override
	public List<Nucleo> findAllnucleo(String userdb) {

		
		String sql = "select * from nucleo where nuc_estado = 1;" ;
		List<Nucleo>  nucleos = null;
		try {
			nucleos = jdbcTemplate.query(sql, new NucleoSetExtractor());
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return nucleos;
	}

	@Override
	public List<Caracter> findAllCaracter(String userdb) {
		
		String sql = "select * from caracter " ;
		List<Caracter>  caracters = null;
		try {
			caracters = jdbcTemplate.query(sql, new CaracterSetExtractor());
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return caracters;
	}
	

}
