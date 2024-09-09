package com.usco.edu.dao;

public interface IFotoDao {
	
	String obtenerTokenFoto(String atributos);
	
	String obtenerPercodigoEstudiante(String codigo);
	
	String obtenerPercodigoDocente(String codigo);

	String obtenerTokenFotoVisualizar(String atributos);

}
