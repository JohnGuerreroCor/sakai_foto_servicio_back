package com.usco.edu.service;

import java.util.List;

import com.usco.edu.entities.CampoAmplio;
import com.usco.edu.entities.CampoDetallado;
import com.usco.edu.entities.CampoEspecifico;

public interface ICamposService {
	public List<CampoAmplio> camposAmplios(String userdb);

	public List<CampoDetallado> camposDetallados(String userdb);

	public List<CampoEspecifico> camposExpecificos(String userdb);

	public List<CampoDetallado> camposDetalladosByEsp(String userdb, int ce);

	public List<CampoEspecifico> camposExpecificosByAmplio(String userdb, int ca);

	int createCampoAmplio(String userdb, CampoAmplio ca);

	int createCampoEspecifico(String userdb, CampoEspecifico ces);

	int createCampoDetallado(String userdb, CampoDetallado cd);

	int updateCampoAmplio(String userdb, CampoAmplio ca);

	int updateCampoEspecifico(String userdb, CampoEspecifico ce);

	int updateCampoDetallado(String userdb, CampoDetallado cd);

	CampoDetallado camposDetalladosById(String userdb, int id);

}
