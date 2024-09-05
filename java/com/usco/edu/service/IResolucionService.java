package com.usco.edu.service;

import java.util.List;

import com.usco.edu.entities.NormativaTipo;
import com.usco.edu.entities.Resolucion;


public interface IResolucionService {
	
	public List<Resolucion> findAll(String userdb);
	public Resolucion findbyId(String userdb , Long codigo);
	public List<Resolucion> findbyDescripcion(String userdb, String descripcion);
	public int getTotalResolucionesAll(String userdb);
	public int getTotalResolucinesbyDescrip(String userdb);
	public Resolucion createResolucion(String userdb , Resolucion resolucion);
	public void updateResolucion(String userdb, Resolucion resolucion);
	public List<NormativaTipo> normativaTipoAll(String userdb);
}
