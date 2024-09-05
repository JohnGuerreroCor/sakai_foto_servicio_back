package com.usco.edu.dao;

public interface IInicioSesionDao {
	
	public String obtenerTokenInicioSesion(String atributos, String userdb);
	
	public String urlTokenPeticion( String userdb);
	
	public String obtenerSegundaClaveReal(String segundaClave);

}
