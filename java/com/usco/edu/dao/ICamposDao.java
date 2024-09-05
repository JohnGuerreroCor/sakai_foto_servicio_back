package com.usco.edu.dao;

import java.util.List;

import com.usco.edu.entities.CampoAmplio;
import com.usco.edu.entities.CampoDetallado;
import com.usco.edu.entities.CampoEspecifico;

public interface ICamposDao {
	public List<CampoAmplio> camposAmplios(String userdb);

	public List<CampoDetallado> camposDetallados(String userdb);

	public List<CampoEspecifico> camposExpecificos(String userdb);

	public int createCampoAmplio(String userdb, CampoAmplio ca);

	int createCampoEspecifico(String userdb, CampoEspecifico ce);

	int createCampoDetallado(String userdb, CampoDetallado cd);

	List<CampoDetallado> camposDetalladosByEspecifico(String userdb, int ce);

	public List<CampoEspecifico> camposExpecificosByAmplio(String userdb, int ca);

	int updateCampoAmplio(String userdb, CampoAmplio ca);

	int updateCampoEspecifico(String userdb, CampoEspecifico ce);

	int updateCampoDetallado(String userdb, CampoDetallado cd);

	CampoDetallado camposDetalladosById(String userdb, int id);

}
