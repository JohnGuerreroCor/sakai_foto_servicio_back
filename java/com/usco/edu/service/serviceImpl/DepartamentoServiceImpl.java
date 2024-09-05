package com.usco.edu.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usco.edu.dao.IDepartamentoDao;
import com.usco.edu.entities.Departamento;
import com.usco.edu.entities.Municipio;
import com.usco.edu.service.IDepartamentoService;
@Service
public class DepartamentoServiceImpl implements IDepartamentoService{
	
	@Autowired
	IDepartamentoDao depYmunpDao;

	@Override
	public List<Departamento> departamentos(String userdb) {
		return depYmunpDao.departamentos(userdb);
	}

	@Override
	public List<Municipio> municipiosbydep(String userdb) {
		return depYmunpDao.municipiosbydep(userdb);
	}
	


}
