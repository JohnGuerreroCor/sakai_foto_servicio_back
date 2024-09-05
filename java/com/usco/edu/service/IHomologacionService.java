package com.usco.edu.service;

import java.util.List;

import com.usco.edu.entities.Homologacion;
import com.usco.edu.entities.HomologacionTipo;
import com.usco.edu.entities.SimulacionHomo;
import com.usco.edu.entities.Traslado;

public interface IHomologacionService {
	List<SimulacionHomo> SimularHomologaciones(String userdb, String est_codigo, int pla_old, int pla_new);
	public List<HomologacionTipo> Homotipoall(String userdb);
	public boolean realizarHomologaciones(String userdb, String est_codigo,Homologacion homo);
	public boolean crearHomologacion(String userdb,Homologacion homologacion);
	public boolean borrarHomologacion(String userdb,Homologacion homologacion);
	public List<SimulacionHomo> SimularHomologacionesSinEst_codigo(String userdb,Homologacion homologacion);
	public boolean validarHomologacion(String userdb , Homologacion homologacion);
	
	List<SimulacionHomo> SimularTrasladoAsignaturas(String userdb, String est_codigo, int pla_old, int pla_new);
	public boolean realizarTraslados(String userdb, String est_codigo,Homologacion homo);
	public List<Traslado> AsignaturasforTraslado(String userdb,Homologacion homologacion);
	public boolean crearTraslado(String userdb,Homologacion homologacion);

}
