package com.usco.edu.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usco.edu.entities.CampoAmplio;
import com.usco.edu.entities.CampoDetallado;
import com.usco.edu.entities.CampoEspecifico;
import com.usco.edu.service.ICamposService;

@RestController
@RequestMapping(path = "campos")
public class CamposRestController {

	@Autowired
	private ICamposService service;

	@PostMapping(path = "amplio/{user}")
	public int crearCamposAmplios(@RequestBody CampoAmplio ca, @PathVariable String user) {

		return service.createCampoAmplio(user, ca);
	}

	@PostMapping(path = "especifico/{user}")
	public int crearCamposEspecificos(@RequestBody CampoEspecifico ce, @PathVariable String user) {

		return service.createCampoEspecifico(user, ce);
	}

	@PostMapping(path = "detallado/{user}")
	public int crearCamposDetallados(@RequestBody CampoDetallado cd, @PathVariable String user) {

		return service.createCampoDetallado(user, cd);
	}

	@GetMapping(path = "amplio-get/{user}")
	public List<CampoAmplio> getAm(@PathVariable String user) {

		return service.camposAmplios(user);
	}

	@GetMapping(path = "especifico-get/{user}")
	public List<CampoEspecifico> getEsp(@PathVariable String user) {

		return service.camposExpecificos(user);
	}

	@GetMapping(path = "detallado-get/{user}")
	public List<CampoDetallado> getDet(@PathVariable String user) {

		return service.camposDetallados(user);
	}

	@GetMapping(path = "especifico/{user}/{ca}")
	public List<CampoEspecifico> getDetByAmplio(@PathVariable String user, @PathVariable int ca) {

		return service.camposExpecificosByAmplio(user, ca);
	}

	@GetMapping(path = "detallado/{user}/{ce}")
	public List<CampoDetallado> getDetByEsp(@PathVariable String user, @PathVariable int ce) {

		return service.camposDetalladosByEsp(user, ce);
	}

	@GetMapping(path = "detallado-id/{user}/{id}")
	public CampoDetallado getDetById(@PathVariable String user, @PathVariable int id) {

		return service.camposDetalladosById(user, id);
	}

	@PutMapping(path = "update-ca/{user}")
	public int updateCampoAmplio(@PathVariable String user, @RequestBody CampoAmplio ca) {
		return service.updateCampoAmplio(user, ca);
	}

	@PutMapping(path = "update-ce/{user}")
	public int updateCampoEspecifico(@PathVariable String user, @RequestBody CampoEspecifico ce) {
		return service.updateCampoEspecifico(user, ce);
	}

	@PutMapping(path = "update-cd/{user}")
	public int updateCampDetallado(@PathVariable String user, @RequestBody CampoDetallado cd) {
		return service.updateCampoDetallado(user, cd);
	}

}
