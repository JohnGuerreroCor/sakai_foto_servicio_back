package com.usco.edu.service;

import java.util.List;

import com.usco.edu.entities.Departamento;
import com.usco.edu.entities.Municipio;

public interface IDepartamentoService {
	public List<Departamento> departamentos(String userdb);
	public List<Municipio> municipiosbydep(String userdb);
}
