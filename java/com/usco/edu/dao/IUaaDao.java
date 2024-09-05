package com.usco.edu.dao;

import java.util.List;

import com.usco.edu.entities.Uaa;
import com.usco.edu.entities.UaaTipo;

public interface IUaaDao {
	
	public List<Uaa> allUaa(String userdb);
	public List<UaaTipo> uaaTipos(String userdb);
	public List<Uaa> findByTipoUaa(String userdb, int tipoUaa);
	public List<Uaa> findByName(String userdb, String name);
	public List<Uaa> findByTipoUaaPag(String userdb, int tipoUaa,int page, int records);
	public List<Uaa> findBySede(String userdb, int sedeCod);
	public List<Uaa> listUaaUnificadas(String userdb);
	public Uaa findById(String userdb, int UaaCodigo);
	public int getTotalUaabyTipo(String userdb, int tipoUaa);
	public int getTotalUaa(String userdb);
	public int getTotalUaaByName(String userdb,String name);
	public int validarIdDeLaUaa(String userdb, int UaaCodigo);
	public Uaa newUaa(String userdb, Uaa uaa);
	public void newUaaDpto(String userdb, Uaa uaa);
	public boolean updateUaa(String userdb , Uaa uaa);
	public boolean validarUaaDpto(Uaa uaa);
	
}
