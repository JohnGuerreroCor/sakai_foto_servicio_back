package com.usco.edu.dao;

import java.util.List;

import com.usco.edu.entities.NormaAcreditacion;

public interface INormaAcreditacionDao {

	List<NormaAcreditacion> find(String userdb);

	List<NormaAcreditacion> findbyId(int codigo, String userdb);

	void create(NormaAcreditacion na, String userdb);

	void update(NormaAcreditacion na, String userdb);

}
