package com.usco.edu.service;

import com.usco.edu.entities.Estudiante;

public interface IEstudianteService {
	public Estudiante findbyCodigo(String codigoEst, String userdb);
}
