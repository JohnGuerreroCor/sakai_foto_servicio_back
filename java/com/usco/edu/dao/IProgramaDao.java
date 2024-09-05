package com.usco.edu.dao;

import java.util.List;

import com.usco.edu.dto.ReportePrograma;
import com.usco.edu.entities.Convenio;
import com.usco.edu.entities.Estado;
import com.usco.edu.entities.Jornada;
import com.usco.edu.entities.Modalidad;
import com.usco.edu.entities.NivelAcademico;
import com.usco.edu.entities.Programa;
import com.usco.edu.entities.Snies_nbc;

public interface IProgramaDao {

	public List<Programa> progromasBySede(Long sed_codigo, String userdb);

	public List<Programa> progromasAll(String userdb);

	public int totalprogramas(String userdb);

	public Programa programaByUaa(Long uaaCodigo, String userdb);

	public Programa programaSimpleByUaa(Long uaaCodigo, String userdb);

	public Programa findById(Long proCodigo, String userdb);

	public Programa findByIdSimple(Long proCodigo, String userdb);

	public List<Jornada> jornadasAll(String userdb);

	public List<Modalidad> modalidadAll(String userdb);

	public List<NivelAcademico> nivelAcademicoAll(String userdb);

	public List<Convenio> convenioAll(String userdb);

	public Programa newPrograma(Programa programa, String userdb);

	public void updatePrograma(Programa programa, String userdb);

	public List<Estado> estadosPrograma(String userdb);

	public List<Snies_nbc> nbcAll(String userdb);

	public boolean validarNivelAcademico(Long proCodigo, String userdb);

	public List<Programa> programabySnies(String sniesCod, String userdb);

	public ReportePrograma datosReporte(Long proCodigo);

	public List<Programa> progromasbyModalidad(String userdb, int modalidad);

	public List<Programa> progromasbyNivelAcademico(String userdb, int nivelAcademico);
}
