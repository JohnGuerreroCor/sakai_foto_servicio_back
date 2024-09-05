package com.usco.edu.dao;

import java.util.List;

import com.usco.edu.entities.NormaNivelAcademico;

public interface INormaNivelAcademicoDao {

	List<NormaNivelAcademico> find(String userdb);

	List<NormaNivelAcademico> findbyId(Long codigo, String userdb);

	void create(NormaNivelAcademico na, String userdb);

	void update(NormaNivelAcademico na, String userdb);
}
