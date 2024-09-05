package com.usco.edu.dao;

import java.util.List;

import com.usco.edu.entities.Caracter;
import com.usco.edu.entities.Nucleo;

public interface IDatosAsignaturaDao {
	public List<Nucleo> findAllnucleo(String userdb);
	public List<Caracter> findAllCaracter(String userdb);

}
