package com.usco.edu.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usco.edu.dao.ICamposDao;
import com.usco.edu.entities.CampoAmplio;
import com.usco.edu.entities.CampoDetallado;
import com.usco.edu.entities.CampoEspecifico;
import com.usco.edu.service.ICamposService;

@Service
public class CampoServiceImpl implements ICamposService {

	@Autowired
	private ICamposDao camposDao;

	@Override
	public List<CampoAmplio> camposAmplios(String userdb) {
		return camposDao.camposAmplios(userdb);
	}

	@Override
	public List<CampoDetallado> camposDetallados(String userdb) {
		return camposDao.camposDetallados(userdb);
	}

	@Override
	public List<CampoEspecifico> camposExpecificos(String userdb) {
		return camposDao.camposExpecificos(userdb);
	}

	@Override
	public int createCampoAmplio(String userdb, CampoAmplio ca) {

		return camposDao.createCampoAmplio(userdb, ca);

	}

	@Override
	public int createCampoEspecifico(String userdb, CampoEspecifico ce) {

		try {

			return camposDao.createCampoEspecifico(userdb, ce);

		} catch (Exception e) {
			System.out.println(e);
			return 0;
			// TODO: handle exception
		}

	}

	@Override
	public int createCampoDetallado(String userdb, CampoDetallado cd) {

		try {

			return camposDao.createCampoDetallado(userdb, cd);

		} catch (Exception e) {
			System.out.println(e);
			return 0;
			// TODO: handle exception
		}

	}

	@Override
	public List<CampoDetallado> camposDetalladosByEsp(String userdb, int ce) {
		// TODO Auto-generated method stub
		return camposDao.camposDetalladosByEspecifico(userdb, ce);
	}

	@Override
	public CampoDetallado camposDetalladosById(String userdb, int id) {
		// TODO Auto-generated method stub
		return camposDao.camposDetalladosById(userdb, id);
	}

	@Override
	public List<CampoEspecifico> camposExpecificosByAmplio(String userdb, int ca) {
		// TODO Auto-generated method stub
		return camposDao.camposExpecificosByAmplio(userdb, ca);
	}

	@Override
	public int updateCampoAmplio(String userdb, CampoAmplio ca) {

		return camposDao.updateCampoAmplio(userdb, ca);

	}

	@Override
	public int updateCampoEspecifico(String userdb, CampoEspecifico ce) {

		return camposDao.updateCampoEspecifico(userdb, ce);

	}

	@Override
	public int updateCampoDetallado(String userdb, CampoDetallado cd) {

		return camposDao.updateCampoDetallado(userdb, cd);

	}

}
