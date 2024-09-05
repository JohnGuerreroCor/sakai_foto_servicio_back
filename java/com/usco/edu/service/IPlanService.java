package com.usco.edu.service;

import java.util.List;
import com.usco.edu.entities.Plan;

public interface IPlanService {
	public List<Plan> planesPorPrograma(int pro_codigo,String userdb);
	public List<Plan> findByCodigoList(int pla_codigo,String userdb);
	public List<Plan> planesPorUaa(int uaa_codigo , String userdb);
	public Plan findByCodigo(int pla_codigo,String userdb);
	public Plan createPlan(Plan plan, String userLogeado);
	public void updatePlan(Plan plan, String userLogeado);
	public List<Plan> findByNombre(String nombre, String userdb);
	public List<Plan> findByCodigotraslados(String userdb, int pla_codigo);
}
