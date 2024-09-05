package com.usco.edu.service;

import java.util.List;

import com.usco.edu.entities.Uaa;
import com.usco.edu.entities.UaaTipo;

public interface IUaaService {
	public List<Uaa> allUaa(String userdb);
	public List<UaaTipo> uaaTipos(String userdb);
	public List<Uaa> findByTipoUaa(String userdb, int tipoUaa);
	public List<Uaa> findByName(String userdb, String name);
	public Uaa findById(String userdb, int UaaCodigo);
	public int getTotalUaabyTipo(String userdb, int tipoUaa);
	public int getTotalUaa(String userdb );
	public int getTotalUaaByName(String userdb,String name);
	public boolean validarIdDeLaUaa(String userdb, int UaaCodigo);
	public Uaa newUaa(String userdb, Uaa uaa);
	public boolean newUaaDpto(String userdb, Uaa uaa);
	public boolean updateUaa(String userdb , Uaa uaa);
	public List<Uaa> findBySede(String userdb, int sedeCod);
	public List<Uaa> listUaaUnificadas(String userdb);
	public boolean validarUaaDpto(Uaa uaa);


}