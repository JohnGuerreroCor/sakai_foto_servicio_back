package com.usco.edu.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usco.edu.dao.IAsignaturaDao;
import com.usco.edu.dao.IPaaDao;
import com.usco.edu.entities.Asignatura;
import com.usco.edu.entities.Componente;
import com.usco.edu.entities.Paa;
import com.usco.edu.service.IPaaService;
@Service
public class PaaServiceImpl implements IPaaService{
	
	@Autowired
	IPaaDao paaDao;
	
	@Autowired
	IAsignaturaDao asigDao;
	

	@Override
	public List<Paa> findbyIdPlan(int pla_codigo,String userdb) {
		return paaDao.findbyIdPlan(pla_codigo , userdb);
	}


	@Override
	public Paa findbyId(int codigo, String userdb) {
		return paaDao.findById(codigo, userdb);
	}


	@Override
	public List<Componente> allComponent(String userdb) {
		return paaDao.allComponent(userdb);
	}


	@Override
	public void updatePaa(Paa paa, String userdb) {
		paaDao.updatePaa(paa, userdb);
		
	}

	@Override
	public void crearPaa(Paa paa, String userdb) {
		paaDao.crearPaa(paa, userdb);
		
	}


	@Override
	public void copiarPlan(int pla_codigo, int planCopia, String userdb) {
		List<Paa> paaCopia = paaDao.findbyIdPlan(planCopia , userdb);
		List<Asignatura> asiEqui= new ArrayList<Asignatura>();
		List<Asignatura> asipre= new ArrayList<Asignatura>();
		String precredito ;

		for(Paa paa : paaCopia) {
			paa.setPlan(pla_codigo);
			if(!paaDao.validarPaa(pla_codigo, paa)) {
				paaDao.crearPaa(paa, userdb);
			}
			
			asiEqui = asigDao.obtenerEquivalencias(planCopia, paa.getAsignatura().getCodigo(), userdb);
			
			for(Asignatura asi : asiEqui) {
				if(!asigDao.validarEquivalencia(pla_codigo, paa.getAsignatura().getCodigo(),  asi.getCodigo())) {
					asigDao.crearEquivalencia(paa, asi.getCodigo(), userdb);

				}
			}
			
			asipre = asigDao.obtenerPrerrequisitos(planCopia,  paa.getAsignatura().getCodigo(), userdb);
			
			for(Asignatura asi : asipre) {
				if(!asigDao.validarPrerrequisito(pla_codigo, paa.getAsignatura().getCodigo(), asi.getCodigo())) {
					asigDao.crearPrerrequisito(paa, asi.getCodigo(), userdb);

				}
			}
			
			precredito = asigDao.obtenerPrerrequisitoCredito(planCopia, paa.getAsignatura().getCodigo(), userdb);
			if(!precredito.equals("0")) {
				if(!asigDao.validarPrerrequisitoencredito(pla_codigo, paa.getAsignatura().getCodigo(), Integer.parseInt(precredito))) {
					asigDao.crearPrerrequisitoCredito(paa, Integer.parseInt(precredito), userdb);
				}
			}
			
			
		}
	}

}
