package com.usco.edu.dao;

import java.util.List;

import com.usco.edu.entities.Componente;
import com.usco.edu.entities.Paa;

public interface IPaaDao{
	
	List<Paa>findbyIdPlan(int pla_codigo,String userdb);
	Paa findById(int codigo , String userdb);
	List<Componente> allComponent(String userdb);
	void updatePaa(Paa paa, String userdb);
	void crearPaa(Paa paa, String userdb);
	boolean validarPaa(int pla_codigo,Paa paa);
}
