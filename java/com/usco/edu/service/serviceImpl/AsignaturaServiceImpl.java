package com.usco.edu.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usco.edu.dao.IAsignaturaDao;
import com.usco.edu.entities.Asignatura;
import com.usco.edu.entities.Paa;
import com.usco.edu.service.IAsignaturaService;
@Service
public class AsignaturaServiceImpl implements IAsignaturaService {
	@Autowired
	IAsignaturaDao asignaturaDao;
	
	@Override
	public List<Asignatura> findAll(String userdb) {
		List<Asignatura> asignatura = asignaturaDao.findAll(userdb);
		 for(Asignatura asi: asignatura) {
			 asi.setNombre(asi.getCodigo()+" -- "+asi.getNombre());
			}
		 
		return asignatura;
	}

	@Override
	public List<Asignatura> obtenerPrerrequisitos(int pla_codigo, int asi_codigo, String userdb) {
		return asignaturaDao.obtenerPrerrequisitos(pla_codigo, asi_codigo, userdb);
	}

	@Override
	public List<Asignatura>  obtenerEquivalencias(int pla_codigo, int asi_codigo, String userdb) {
		return asignaturaDao.obtenerEquivalencias(pla_codigo, asi_codigo, userdb);
	}

	@Override
	public String obtenerPrerrequisitoCredito(int pla_codigo, int asi_codigo, String userdb) {
		return asignaturaDao.obtenerPrerrequisitoCredito(pla_codigo, asi_codigo, userdb);
	}

	@Override
	public void crearPrerrequisito(Paa paa, int asignatura, String userdb) {
		asignaturaDao.crearPrerrequisito(paa, asignatura, userdb);
		
	}

	@Override
	public void crearEquivalencia(Paa paa, int asignatura, String userdb) {
		asignaturaDao.crearEquivalencia(paa, asignatura, userdb);
		
	}

	@Override
	public List<Asignatura> AsignaturasPrerrequisitos(int pla_codigo, String userdb) {
		List<Asignatura> asignatura = asignaturaDao.AsignaturasPrerrequisitos(pla_codigo, userdb);
		 for(Asignatura asi: asignatura) {
			 asi.setNombre(asi.getCodigo()+" -- "+asi.getNombre());
			}
		 
		return asignatura;
	}

	@Override
	public Asignatura findById(String userdb, int codigo) {
		return asignaturaDao.findById(userdb, codigo);
	}

	@Override
	public List<Asignatura> findbyName(String userdb, String asi_nombre) {
		return asignaturaDao.findbyName(userdb, asi_nombre);
	}

	@Override
	public void crearPrerrequisitoCredito(Paa paa, int creditos, String userdb) {
		asignaturaDao.crearPrerrequisitoCredito(paa, creditos, userdb);
		
	}

	@Override
	public List<Asignatura> findbyUaa(String userdb, int uaa) {
		return asignaturaDao.findbyUaa(userdb, uaa);
	}

	@Override
	public Asignatura crearAsignatura(Asignatura asignatura, String userdb) {
		return asignaturaDao.crearAsignatura(asignatura, userdb);
	}

	@Override
	public boolean updateAsignatura(Asignatura asignatura, String userdb) {
		return asignaturaDao.updateAsignatura(asignatura, userdb);
	}

	@Override
	public List<Asignatura> findbyNombreCorto(String userdb, String nombreCorto) {
		return asignaturaDao.findbyNombreCorto(userdb, nombreCorto);
	}

	@Override
	public void eliminarEquivalencia(Paa paa, int asignatura, String userdb) {
		asignaturaDao.eliminarEquivalencia(paa, asignatura, userdb);	
	}

	@Override
	public void eliminarPrerrequisito(Paa paa, int asignatura, String userdb) {
		asignaturaDao.eliminarPrerrequisito(paa, asignatura, userdb);
	}

	@Override
	public void eliminarPrerrequisitoenCredito(Paa paa, int creditos, String userdb) {
		asignaturaDao.eliminarPrerrequisitoenCredito(paa, creditos, userdb);
	}

}
