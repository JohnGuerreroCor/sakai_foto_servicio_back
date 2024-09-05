package com.usco.edu.dao;

import com.usco.edu.entities.Estudiante;

public interface IEstudianteDao {
	public Estudiante findbyCodigo(String codigoEst, String userdb);
	
}
