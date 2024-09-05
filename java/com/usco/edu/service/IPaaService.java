package com.usco.edu.service;

import java.util.List;

import com.usco.edu.entities.Componente;
import com.usco.edu.entities.Paa;

public interface IPaaService {
	public List<Paa> findbyIdPlan(int pla_codigo , String userdb);
	public Paa findbyId(int codigo , String userdb);
	public List<Componente> allComponent(String userdb);
	public void updatePaa(Paa paa, String userdb);
	public void crearPaa(Paa paa, String userdb);
	public void copiarPlan(int pla_codigo,int planCopia, String userdb);
}
