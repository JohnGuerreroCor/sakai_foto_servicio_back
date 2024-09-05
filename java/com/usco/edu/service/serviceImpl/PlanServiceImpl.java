package com.usco.edu.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usco.edu.dao.IPlanDao;
import com.usco.edu.entities.Plan;
import com.usco.edu.service.IPlanService;

@Service
public class PlanServiceImpl implements IPlanService {
	
	@Autowired
	IPlanDao planDao;


	@Override
	public List<Plan> planesPorPrograma(int pro_codigo ,String userdb) {
		return planDao.planesPorPrograma(pro_codigo,userdb);
	}

	@Override
	public List<Plan> findByCodigoList(int pla_codigo, String userdb) {
		return planDao.findByCodigoList(pla_codigo,userdb);
	}

	@Override
	public Plan findByCodigo(int pla_codigo ,String userdb) {
		return planDao.findByCodigo(pla_codigo,userdb);
	}

	@Override
	public Plan createPlan(Plan plan, String userLogeado) {
		return planDao.createPlan(plan, userLogeado);
	}

	@Override
	public void updatePlan(Plan plan, String userLogeado) {
		planDao.updatePlan(plan, userLogeado);
		
	}

	@Override
	public List<Plan> planesPorUaa(int uaa_codigo, String userdb) {
		return planDao.planesPorUaa(uaa_codigo, userdb);
	}

	@Override
	public List<Plan> findByNombre(String nombre, String userdb) {
		return planDao.findByNombre(nombre, userdb);
	}

	@Override
	public List<Plan> findByCodigotraslados(String userdb, int pla_codigo) {
		return planDao.findByCodigotraslados(userdb, pla_codigo);
	}
	
}
