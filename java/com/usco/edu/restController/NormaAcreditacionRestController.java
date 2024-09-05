package com.usco.edu.restController;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import org.codehaus.jackson.map.ObjectMapper;
import com.usco.edu.entities.NormaAcreditacion;
import com.usco.edu.service.INormaAcreditacionService;

@RestController
@RequestMapping(path = "norma")
public class NormaAcreditacionRestController {

	@Autowired
	private INormaAcreditacionService service;

	@GetMapping("find/{user}")
	public List<NormaAcreditacion> find(@PathVariable String user) {
		return service.find(user);
	}

	@GetMapping("find-id/{codigo}/{user}")
	public List<NormaAcreditacion> findById(@PathVariable String user, @PathVariable int codigo) {

		return service.findbyId(codigo, user);
	}

	@PostMapping("create/{user}/{perCodigo}/{uaa}")
	public void create(@PathVariable String user, @RequestPart MultipartFile archivo, @RequestParam String json,
			HttpServletRequest request, @PathVariable Long perCodigo, @PathVariable int uaa) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		NormaAcreditacion na = objectMapper.readValue(json, NormaAcreditacion.class);
		na.setPdf(service.subirPdf(archivo, perCodigo, uaa, user, request));
		service.create(na, user);

	}

	@PutMapping("update/{user}")
	public void update(@PathVariable String user, @RequestBody NormaAcreditacion na) {
		service.update(na, user);
	}

}
