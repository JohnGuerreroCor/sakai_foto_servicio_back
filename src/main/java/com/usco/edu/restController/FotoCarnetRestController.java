package com.usco.edu.restController;

import java.io.ByteArrayInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.usco.edu.dto.Foto;
import com.usco.edu.service.IFotoService;

@RestController
@RequestMapping(path = "foto")
public class FotoCarnetRestController {
	
	@Autowired
	private IFotoService service;
	
	@PostMapping("subir/{user}/{perCodigo}/{uaa}")
	public void create(@PathVariable String user, @RequestPart MultipartFile foto,
			HttpServletRequest request, @PathVariable String perCodigo, @PathVariable int uaa) {
		
		System.out.println("Entro al controlador");
		service.subirFoto(foto, perCodigo, uaa, user, request);
	}
	
	@GetMapping("obtener-foto/{user}/{codigo}")
	public ResponseEntity<InputStreamResource> foto(@PathVariable String user, HttpServletResponse response, @PathVariable String codigo) throws Exception{
		ByteArrayInputStream stream = service.mirarFoto(codigo, user, response);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=\" foto.png\"");

		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(stream));
		
	}
	
	@GetMapping(path = "obtener-foto-antigua/{user}/{codigo}")
	public Foto fotoAntigua(@PathVariable String user, @PathVariable String codigo) throws Exception{
		return service.mirarFotoAntigua(codigo, user);
	}
}
