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

import com.usco.edu.entities.NormaNivelAcademico;
import com.usco.edu.service.INormaNivelAcademicoService;

@RestController
@RequestMapping(path = "norma-nia")
public class NormaNivelAcademicorestController {

	@Autowired
	private INormaNivelAcademicoService service;

	@GetMapping("find/{user}")
	public List<NormaNivelAcademico> find(@PathVariable String user) {
		return service.find(user);
	}

	@GetMapping("find-id/{codigo}/{user}")
	public List<NormaNivelAcademico> findById(@PathVariable String user, @PathVariable Long codigo) {

		return service.findbyId(codigo, user);
	}

	@PostMapping("create/{user}")
	public void create(@RequestBody NormaNivelAcademico na, @PathVariable String user) {
		service.create(na, user);
	}

	@PutMapping("update/{user}")
	public void update(@PathVariable String user, @RequestBody NormaNivelAcademico na) {
		service.update(na, user);
	}
}
