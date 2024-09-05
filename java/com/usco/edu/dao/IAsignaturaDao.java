package com.usco.edu.dao;

import java.util.List;

import com.usco.edu.entities.Asignatura;
import com.usco.edu.entities.Paa;

public interface IAsignaturaDao {
	
	public List<Asignatura> findAll(String userdb);
	public List<Asignatura> obtenerPrerrequisitos(int pla_codigo ,int asi_codigo ,String userdb);
	//son las asignaturas disponibles para un prerrequisito
	public List<Asignatura> AsignaturasPrerrequisitos(int pla_codigo ,String userdb);
	
	public List<Asignatura> obtenerEquivalencias(int pla_codigo ,int asi_codigo ,String userdb);
	public boolean validarEquivalencia(int pla_codigo , int asi_codigo , int asi_equivalencia);
	public boolean validarPrerrequisito(int pla_codigo , int asi_codigo , int asi_prerrequisito);
	public boolean validarPrerrequisitoencredito(int pla_codigo , int asi_codigo , int creditos);
	public String obtenerPrerrequisitoCredito(int pla_codigo ,int asi_codigo ,String userdb);
	public void crearPrerrequisito(Paa paa, int asignatura,String userdb);
	public void crearPrerrequisitoCredito(Paa paa, int creditos,String userdb);
	public void crearEquivalencia(Paa paa, int asignatura,String userdb);
	public void eliminarEquivalencia(Paa paa, int asignatura,String userdb);
	public void eliminarPrerrequisito(Paa paa, int asignatura,String userdb);
	public void eliminarPrerrequisitoenCredito(Paa paa,int creditos ,String userdb);
	
	public Asignatura findById(String userdb, int codigo);
	public List<Asignatura> findbyName(String userdb , String asi_nombre);
	public List<Asignatura> findbyNombreCorto(String userdb , String nombreCorto);
	public List<Asignatura> findbyUaa(String userdb , int uaa);
	public Asignatura crearAsignatura(Asignatura asignatura,String userdb);
	public boolean updateAsignatura(Asignatura asignatura,String userdb);

}
