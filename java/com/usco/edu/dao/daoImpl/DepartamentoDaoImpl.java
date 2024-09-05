package com.usco.edu.dao.daoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.usco.edu.dao.IDepartamentoDao;
import com.usco.edu.entities.Departamento;
import com.usco.edu.entities.Municipio;
import com.usco.edu.resultSetExtractor.DepartamentoSerExtractor;
import com.usco.edu.resultSetExtractor.MunicipioSetExtractor;

@Repository
public class DepartamentoDaoImpl implements IDepartamentoDao{
	
	
	@Autowired
	@Qualifier("JDBCTemplatePlanesConsulta")
	public JdbcTemplate jdbcTemplate;

	@Override
	public List<Departamento> departamentos(String userdb) {

		String sql = " select * from departamento where pai_codigo = 21";
		List<Departamento> departamentos =null ;
		try {
			departamentos = jdbcTemplate.query(sql, new DepartamentoSerExtractor());
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return departamentos;
	}

	@Override
	public List<Municipio> municipiosbydep(String userdb) {

		String sql = " select * from municipio where dep_codigo = 19";
		List<Municipio> municipios =null ;
		try {
			municipios = jdbcTemplate.query(sql,new MunicipioSetExtractor());
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return municipios;
	}
	


}
