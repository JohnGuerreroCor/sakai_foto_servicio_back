package com.usco.edu.service.serviceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.usco.edu.dao.IProgramaDao;
import com.usco.edu.entities.Convenio;
import com.usco.edu.entities.Estado;
import com.usco.edu.entities.Jornada;
import com.usco.edu.entities.Modalidad;
import com.usco.edu.entities.NivelAcademico;
import com.usco.edu.entities.Programa;
import com.usco.edu.entities.Snies_nbc;
import com.usco.edu.service.IProgramaService;

@Service
public class ProgramaServiceImpl implements IProgramaService{
	
	@Autowired
	IProgramaDao programaDao;


	@Override
	public List<Programa> progromasBySede(Long sed_codigo,String userdb) {
		return programaDao.progromasBySede(sed_codigo,userdb);
	}

	@Override
	public List<Programa> progromasAll(String userdb) {
		return programaDao.progromasAll(userdb);
		}

	@Override
	public Programa programaByUaa(Long uaaCodigo, String userdb) {
		Programa programa = programaDao.programaSimpleByUaa(uaaCodigo, userdb);
		
		if(programaDao.validarNivelAcademico(programa.getCodigo(), userdb)) {
			programa = programaDao.findByIdSimple(programa.getCodigo(), userdb);
		}else {
			programa = programaDao.findById(programa.getCodigo(), userdb);

		}
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		
		 Date date1 = null;
		try {
			date1 = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(programa.getFecha_creacion());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  

		String formattedDate = formatter.format(date1.getTime());
		programa.setFecha_creacion(formattedDate);
		return programa;
	}

	@Override
	public List<Jornada> jornadasAll(String userdb) {
		return programaDao.jornadasAll(userdb);
	}

	@Override
	public List<Modalidad> modalidadAll(String userdb) {
		return programaDao.modalidadAll(userdb);
	}

	@Override
	public List<NivelAcademico> nivelAcademicoAll(String userdb) {
		return programaDao.nivelAcademicoAll(userdb);
	}

	@Override
	public List<Convenio> convenioAll(String userdb) {
		return programaDao.convenioAll(userdb);
	}

	@Override
	public List<Estado> estadosPrograma(String userdb) {
		return programaDao.estadosPrograma(userdb);
	}

	@Override
	public List<Snies_nbc> nbcAll(String userdb) {
		return programaDao.nbcAll(userdb);
	}
	
	@Override
	public void updatePrograma(Programa programa, String userdb) {
		programaDao.updatePrograma(programa, userdb);
	}
	
	@Transactional
	@Override
	public Programa findById(Long programa, String userdb) {
		if(programaDao.validarNivelAcademico(programa, userdb)) {
			return programaDao.findByIdSimple(programa, userdb);
		}else {
			return programaDao.findById(programa, userdb);

		}
	}

	@Override
	public Programa newPrograma(Programa programa, String userdb) {
		return programaDao.newPrograma(programa, userdb);
		
	}

	@Override
	public Programa findByIdSimple(Long proCodigo, String userdb) {
		return programaDao.findByIdSimple(proCodigo, userdb);
	}

	@Override
	public Programa programaSimpleByUaa(Long uaaCodigo, String userdb) {
		return programaDao.programaSimpleByUaa(uaaCodigo, userdb);
	}

	@Override
	public List<Programa> programabySnies(String sniesCod, String userdb) {
		return programaDao.programabySnies(sniesCod, userdb);
	}

	@Override
	public int totalprogramas(String userdb) {
		return programaDao.totalprogramas(userdb);
	}

	@Override
	public List<Programa> progromasbyModalidad(String userdb, int modalidad) {
		return programaDao.progromasbyModalidad(userdb, modalidad);
	}

	@Override
	public List<Programa> progromasbyNivelAcademico(String userdb, int nivelAcademico) {
		return programaDao.progromasbyNivelAcademico(userdb, nivelAcademico);
	}
	
	
}
