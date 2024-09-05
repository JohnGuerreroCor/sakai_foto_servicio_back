package com.usco.edu.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usco.edu.dao.IAdministrativoDao;
import com.usco.edu.service.IAdministradorService;
@Service
public class AdministradorServiceImpl implements IAdministradorService{
	
	@Autowired
	private IAdministrativoDao adminDao;

	@Override
	public String getTokenInicioSesion(String atributos, String userdb) {
		return adminDao.getTokenInicioSesion(atributos, userdb);
	}

	@Override
	public String urltokenPeticion(String userdb) {
		return adminDao.urltokenPeticion(userdb);
	}

}
