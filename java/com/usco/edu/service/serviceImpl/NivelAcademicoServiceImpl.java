package com.usco.edu.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usco.edu.dao.INivelAcademicoDao;
import com.usco.edu.entities.NivelAcademico;
import com.usco.edu.service.INivelAcademicoService;

@Service
public class NivelAcademicoServiceImpl implements INivelAcademicoService {

	@Autowired
	private INivelAcademicoDao dao;

	@Override
	public List<NivelAcademico> findPregradoPosgrado(String userdb) {

		return dao.findPregradoPostgrado(userdb);
	}

}
