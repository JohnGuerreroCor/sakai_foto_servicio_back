package com.usco.edu.restController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.usco.edu.entities.NormativaTipo;
import com.usco.edu.entities.Resolucion;
import com.usco.edu.service.IResolucionService;

@RestController
public class ResolucionRestController {
	
	@Autowired
	IResolucionService resolucionService;
	
	@GetMapping("/api/{username}/resoluciones")
	public List<Resolucion> resolucionfindAll(@PathVariable("username") String username) {
		return resolucionService.findAll(username);
	}
	
	@GetMapping("/api/{codigo}/{username}/resolucionbyId")
	public List<Resolucion> listResolucionByid(@PathVariable("username") String username,@PathVariable("codigo") Long codigo) {
		List<Resolucion> resolucion = new ArrayList<Resolucion>();
		 resolucion.add(resolucionService.findbyId(username, codigo));
		 return resolucion;
	}
	
	
	@GetMapping("/api/{username}/getTotalResoAll")
	public int getTotalUaa(@PathVariable("username") String userdb){
		return resolucionService.getTotalResolucionesAll(userdb);
	}
	
	
	@GetMapping("/api/resolucion/{codigo}/{username}")
	public Resolucion resolucionbyId(@PathVariable("username") String username,@PathVariable("codigo") Long codigo) {
		return resolucionService.findbyId(username, codigo);
	}
	
	@PostMapping("/api/resolucion/new/{username}")
	public ResponseEntity<?> crearResolucion(@Valid @RequestBody Resolucion resolucion,BindingResult result,
			@PathVariable("username") String username) {
		Map<String, Object> response = new HashMap<>();
		Resolucion resolucionNew = new Resolucion();
		
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			resolucionNew = resolucionService.createResolucion(username, resolucion);
			
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "La Resolución ha sido creado con éxito.");
		response.put("resolucion", resolucionNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/api/resolucion/{username}/updateresolucion")
	public ResponseEntity<?> updateResolucion(@Valid @RequestBody Resolucion resolucion ,BindingResult result,
            @PathVariable("username") String username) {
	
		Resolucion resoActual = resolucionService.findbyId(username, resolucion.getCodigo().longValue());
		
		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("error", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (resoActual == null) {
			response.put("mensaje", "Error: no se pudo editar, la Resolución ID: "
					.concat(resolucion.getCodigo()+(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
			
		try {
			resoActual.setDependencia(resolucion.getDependencia());
			resoActual.setDescripcion(resolucion.getDescripcion());
			resoActual.setNumero(resolucion.getNumero());
			resoActual.setEstado(resolucion.getEstado());
			
			resolucionService.updateResolucion(username, resolucion);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar la Resolución en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "La Resolución se ha actualizado con éxito.");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/api/{username}/normativaTipos")
	public List<NormativaTipo> normativaTiposAll(@PathVariable("username") String username) {
		return resolucionService.normativaTipoAll(username);
	}
}
