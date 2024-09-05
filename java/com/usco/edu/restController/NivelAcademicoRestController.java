package com.usco.edu.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usco.edu.entities.NivelAcademico;
import com.usco.edu.service.INivelAcademicoService;

@RestController
@RequestMapping(path = "nivel-academico")
public class NivelAcademicoRestController {

	@Autowired
	private INivelAcademicoService service;

	@GetMapping("find-p-p/{user}")
	public List<NivelAcademico> findPregradoPosgrado(@PathVariable String user) {
		return service.findPregradoPosgrado(user);
	}

}
