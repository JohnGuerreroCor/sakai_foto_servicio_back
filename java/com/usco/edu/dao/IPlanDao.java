package com.usco.edu.dao;

import java.util.List;

import com.usco.edu.entities.Plan;

public interface IPlanDao{
	
	//@Query(value = "SELECT * FROM plan_academico pa WHERE pa.pro_codigo = ?",  nativeQuery = true)
	public List<Plan> planesPorPrograma(int pro_codigo, String userdb);
	
	public List<Plan> planesPorUaa(int uaa_codigo , String userdb);
	
	//@Query(value = "SELECT * FROM plan_academico pa WHERE pa.pla_codigo = ?",   nativeQuery = true)
	public List<Plan> findByCodigoList(int pla_codigo, String userdb);
	
	//@Query(value = "SELECT * FROM plan_academico pa WHERE pa.pla_codigo = ?",  nativeQuery = true)
	public Plan findByCodigo(int pla_codigo ,String userdb);
	
	public List<Plan> findByNombre(String nombre ,String userdb);
	
	public Plan createPlan(Plan plan , String userdb);
	
	public void updatePlan(Plan plan , String userdb);
	
	public List<Plan> findByCodigotraslados( String userdb,int pla_codigo);

}
