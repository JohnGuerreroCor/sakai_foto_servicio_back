package com.usco.edu.dao.daoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.usco.edu.dao.IEstudianteDao;
import com.usco.edu.entities.Estudiante;
import com.usco.edu.rowMapper.EstudianteRowMapper;
@Repository
public class EstudianteDaoImpl implements IEstudianteDao{
	

	
	@Autowired
	@Qualifier("JDBCTemplatePlanesConsulta")
	public JdbcTemplate jdbcTemplate;

	@Override
	public Estudiante findbyCodigo(String codigoEst, String userdb) {

		
		String sql = "	select pe.pla_codigo, pa.pla_nombre, " + 
				"	per_apellido + ' ' + per_nombre as nombre, uaa_nombre_corto, pro.uaa_codigo as pro_uaa_codigo , e.est_codigo " + 
				"	from estudiante e " + 
				"	left join persona p on p.per_codigo = e.per_codigo " + 
				"	left join programa pro on pro.pro_codigo = e.pro_codigo " + 
				"	left join uaa on uaa.uaa_codigo = pro.uaa_codigo " + 
				"	left join plan_estudiante pe on pe.est_codigo = e.est_codigo " + 
				"	left join plan_academico pa on pe.pla_codigo = pa.pla_codigo " + 
				"	where e.est_codigo = ? and pe.ple_estado = 1 ";
		Estudiante estudiante = null;
		try {
			estudiante = jdbcTemplate.queryForObject(sql, new Object[] { codigoEst }, new EstudianteRowMapper());
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return estudiante;
	}


}
