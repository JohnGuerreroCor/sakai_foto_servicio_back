package com.usco.edu.dao;

import java.util.List;

import com.usco.edu.entities.NivelAcademico;

public interface INivelAcademicoDao {

	List<NivelAcademico> findPregradoPostgrado(String userdb);
}
