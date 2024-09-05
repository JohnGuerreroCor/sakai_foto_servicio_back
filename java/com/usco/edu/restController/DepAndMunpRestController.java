package com.usco.edu.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.usco.edu.entities.Departamento;
import com.usco.edu.entities.Municipio;
import com.usco.edu.service.IDepartamentoService;

@RestController
public class DepAndMunpRestController {
	
	@Autowired
	IDepartamentoService depAndMunpService;
	
	@GetMapping("/api/departamentos/{username}")
	public List<Departamento> Departamentos(@PathVariable("username") String userdb){
		return depAndMunpService.departamentos(userdb);
	}
	
	@GetMapping("/api/municipios/{username}")
	public List<Municipio> municipiosByDepCodigo(@PathVariable("username") String userdb){
		return depAndMunpService.municipiosbydep(userdb);
	}

}
