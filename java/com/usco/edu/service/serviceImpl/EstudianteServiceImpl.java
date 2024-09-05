package com.usco.edu.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usco.edu.dao.IEstudianteDao;
import com.usco.edu.entities.Estudiante;
import com.usco.edu.service.IEstudianteService;
@Service
public class EstudianteServiceImpl implements IEstudianteService{
	
	@Autowired
	private IEstudianteDao estudianteDao;

	@Override
	public Estudiante findbyCodigo(String codigoEst, String userdb) {
		return estudianteDao.findbyCodigo(codigoEst, userdb);
	}

}
