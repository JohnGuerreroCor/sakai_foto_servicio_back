package com.usco.edu.service;

import java.util.List;

import com.usco.edu.entities.NivelAcademico;

public interface INivelAcademicoService {

	List<NivelAcademico> findPregradoPosgrado(String userdb);
}
