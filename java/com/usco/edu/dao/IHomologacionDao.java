package com.usco.edu.dao;

import java.util.List;

import com.usco.edu.entities.Homologacion;
import com.usco.edu.entities.HomologacionTipo;
import com.usco.edu.entities.SimulacionHomo;
import com.usco.edu.entities.Traslado;

public interface IHomologacionDao {
	public List<SimulacionHomo> SimularHomologaciones(String userdb , String est_codigo, int pla_old , int pla_new);
	public List<SimulacionHomo> SimularTrasladoAsignaturas(String userdb , String est_codigo, int pla_old , int pla_new);
	public boolean insertPlanEstudiante(String userdb , String est_codigo , int pla_new ,String Observacion);
	public boolean desactivarPlanEstudiante(String userdb , String est_codigo, int pla_old);
	public boolean realizarHomologacionesEnMatriculaCurso(String userdb , String est_codigo, SimulacionHomo homolohacion);
	public boolean validarPlanNew(String userdb , String est_codigo , int pla_new);
	public boolean actualizarProgramaenEstudiante(String userdb , String est_codigo , long pro_codigo);
	public List<HomologacionTipo> Homotipoall(String userdb);
	public boolean validarPaaRepetida(String userdb , SimulacionHomo homolohacion);
	public boolean validarHomologacion(String userdb , Homologacion homologacion);
	public boolean activarHomologacion(String userdb , Homologacion homologacion);
	public boolean crearHomologacion(String userdb,Homologacion homologacion);
	public boolean borrarHomologacion(String userdb,Homologacion homologacion);
	public String obtenergrupoHomo(String userdb,Homologacion homologacion);
	public List<SimulacionHomo> SimularHomologacionesSinEst_codigo(String userdb,Homologacion homologacion);
	
	
	List<Traslado> AsignaturasforTraslado(String userdb, Homologacion homologacion);
	public boolean crearTraslado(String userdb, Traslado traslado);
	public boolean validarTraslado(String userdb , Traslado traslado);
	public String obtenergrupoTraslado(String userdb,Traslado traslado);
}
