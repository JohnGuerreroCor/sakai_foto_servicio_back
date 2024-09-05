package com.usco.edu.dao;

public interface IAdministrativoDao {

	public String getTokenInicioSesion(String atributos , String userdb);
	
	public String urltokenPeticion( String userdb);
}
