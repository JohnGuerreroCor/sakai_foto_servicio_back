package com.usco.edu.service;

public interface IInicioSesionService {
	
	public String obtenerTokenInicioSesion(String atributos , String userdb);
	
	public String urlTokenPeticion( String userdb);

}
