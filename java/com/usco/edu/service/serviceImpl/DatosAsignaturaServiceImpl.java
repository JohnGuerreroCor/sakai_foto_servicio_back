package com.usco.edu.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usco.edu.dao.IDatosAsignaturaDao;
import com.usco.edu.entities.Caracter;
import com.usco.edu.entities.Nucleo;
import com.usco.edu.service.IDatosAsignaturaService;

@Service
public class DatosAsignaturaServiceImpl implements IDatosAsignaturaService {
	
	@Autowired
	private IDatosAsignaturaDao datosAsigDao;

	@Override
	public List<Nucleo> findAllnucleo(String userdb) {
		return datosAsigDao.findAllnucleo(userdb);
	}

	@Override
	public List<Caracter> findAllCaracter(String userdb) {
		return datosAsigDao.findAllCaracter(userdb);
	}

}
