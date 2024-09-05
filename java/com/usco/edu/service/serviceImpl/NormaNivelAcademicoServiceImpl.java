package com.usco.edu.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usco.edu.dao.INormaNivelAcademicoDao;
import com.usco.edu.entities.NormaNivelAcademico;
import com.usco.edu.service.INormaNivelAcademicoService;

@Service
public class NormaNivelAcademicoServiceImpl implements INormaNivelAcademicoService {

	@Autowired
	private INormaNivelAcademicoDao dao;

	@Override
	public List<NormaNivelAcademico> find(String userdb) {
		// TODO Auto-generated method stub
		return dao.find(userdb);
	}

	@Override
	public List<NormaNivelAcademico> findbyId(Long codigo, String userdb) {
		// TODO Auto-generated method stub
		return dao.findbyId(codigo, userdb);
	}

	@Override
	public void create(NormaNivelAcademico na, String userdb) {
		// TODO Auto-generated method stub
		dao.create(na, userdb);

	}

	@Override
	public void update(NormaNivelAcademico na, String userdb) {
		// TODO Auto-generated method stub
		dao.update(na, userdb);

	}

}
