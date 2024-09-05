package com.usco.edu.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usco.edu.dao.IResolucionDao;
import com.usco.edu.entities.NormativaTipo;
import com.usco.edu.entities.Resolucion;
import com.usco.edu.service.IResolucionService;

@Service
public class ResolucionServiceImpl implements IResolucionService{
	
	@Autowired
	IResolucionDao resolucionDao;

	@Override
	public List<Resolucion> findAll(String userdb) {
		return resolucionDao.findAll(userdb);
	}

	@Override
	public Resolucion findbyId(String userdb, Long codigo) {
		return resolucionDao.findbyId(userdb, codigo);
	}

	@Override
	public List<Resolucion> findbyDescripcion(String userdb, String descripcion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalResolucionesAll(String userdb) {
		return resolucionDao.getTotalResolucionesAll(userdb);
	}

	@Override
	public int getTotalResolucinesbyDescrip(String userdb) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Resolucion createResolucion(String userdb, Resolucion resolucion) {
		Resolucion resolucionnew = new Resolucion();
		resolucionnew = resolucionDao.createResolucion(userdb, resolucion);
		resolucionnew = findbyId(userdb, resolucionnew.getCodigo());
		return resolucionnew;
	}

	@Override
	public void updateResolucion(String userdb, Resolucion resolucion) {
		resolucionDao.updateResolucion(userdb, resolucion);
	}

	@Override
	public List<NormativaTipo> normativaTipoAll(String userdb) {
		return resolucionDao.normativaTipoAll(userdb);
	}
	
}
