package com.usco.edu.dao;

import java.util.List;

import com.usco.edu.entities.Departamento;
import com.usco.edu.entities.Municipio;

public interface IDepartamentoDao {
	public List<Departamento> departamentos(String userdb);
	public List<Municipio> municipiosbydep(String userdb);
}
