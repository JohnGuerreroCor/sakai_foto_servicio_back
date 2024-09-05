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

import com.usco.edu.entities.Asignatura;
import com.usco.edu.entities.Caracter;
import com.usco.edu.entities.Nucleo;
import com.usco.edu.service.IAsignaturaService;
import com.usco.edu.service.IDatosAsignaturaService;

@RestController
public class AsignaturaRestController {
	
	@Autowired
	IAsignaturaService asignaturaService;
	
	@Autowired
	IDatosAsignaturaService datosAsigService;
	
	@GetMapping("api/asignaturalistbyId/{username}/{codigo}")
	public List<Asignatura> asignaturabyIdList( @PathVariable("username") String username ,@PathVariable("codigo") int codigo) {
		List<Asignatura> asignaturas = new ArrayList<Asignatura>();
		asignaturas.add(asignaturaService.findById(username, codigo));
		return asignaturas ;
	}
	
	@GetMapping("api/asignaturasbyname/{username}/{nombre}")
	public List<Asignatura> asignaturasbyName( @PathVariable("username") String username ,@PathVariable("nombre") String nombre) {
		return  asignaturaService.findbyName(username, nombre);
	}
	
	@GetMapping("api/asignaturasbynombrecorto/{username}/{nombre}")
	public List<Asignatura> asignaturasbyNombreCorto( @PathVariable("username") String username ,@PathVariable("nombre") String nombre) {
		return  asignaturaService.findbyNombreCorto(username, nombre);
	}
	
	@GetMapping("api/asignaturasbyuaa/{username}/{uaa}")
	public List<Asignatura> asignaturasbyUaa( @PathVariable("username") String username ,@PathVariable("uaa") int uaa) {
		return  asignaturaService.findbyUaa(username, uaa);
	}
	
	@GetMapping("api/{username}/caracter")
	public List<Caracter> findAllCaracter( @PathVariable("username") String username) {
		return  datosAsigService.findAllCaracter(username);
	}
	
	@GetMapping("api/{username}/nucleos")
	public List<Nucleo> findAllnucleo( @PathVariable("username") String username) {
		return  datosAsigService.findAllnucleo(username);
	}
	
	@GetMapping("api/asignaturafindById/{username}/{codigo}")
	public Asignatura findById(@PathVariable("codigo") int codigo,@PathVariable("username") String username) {
		return asignaturaService.findById(username, codigo);
	}
	
	
	@PutMapping("/api/updateAsiganatura/{id}/{username}")
	public ResponseEntity<?> updateAsignatura(@Valid @RequestBody Asignatura asignatura, BindingResult result,
			@PathVariable int id, @PathVariable("username") String username) {

		Asignatura asignaturaActual = asignaturaService.findById(username, id);
		
		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("error", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (asignaturaActual == null) {
			response.put("mensaje", "Error: no se pudo editar, la asignatura ID: "
					.concat(id+(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
			
		try {
			asignaturaActual.setNombre(asignatura.getNombre());
			asignaturaActual.setNombreCorto(asignatura.getNombreCorto());
			asignaturaActual.setNombreImpresion(asignatura.getNombreImpresion());
			asignaturaActual.setUaa(asignatura.getUaa());
			asignaturaActual.setPublicar(asignatura.getPublicar());
			asignaturaActual.setCreditos(asignatura.getCreditos());
			asignaturaActual.setCreditos_teoricos(asignatura.getCreditos_teoricos());
			asignaturaActual.setCreditos_practicos(asignatura.getCreditos_practicos());
			asignaturaActual.setCaracter(asignatura.getCaracter());
			asignaturaActual.setTrabajo_presencial(asignatura.getTrabajo_presencial());
			asignaturaActual.setTrabajo_independiente(asignatura.getTrabajo_independiente());
			asignaturaActual.setEstado(asignatura.getEstado());
			asignaturaActual.setNucleo(asignatura.getNucleo());
			asignaturaActual.setNbc(asignatura.getNbc());
			asignaturaActual.setExtramuro(asignatura.getExtramuro());
			
			asignaturaService.updateAsignatura(asignaturaActual, username);
	

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar la asignatura en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "La asignatura se ha actualizado con éxito.");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PostMapping("/api/createasignatura/create/{username}")
	public ResponseEntity<?> createasignatura(@Valid @RequestBody Asignatura asignatura, BindingResult result,
			@PathVariable("username") String username) {
		
		Asignatura asignaturanew = new Asignatura();
		Map<String, Object> response = new HashMap<>();
		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {        
			asignaturanew = asignaturaService.crearAsignatura(asignatura, username);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "La asignatura ha sido creada con éxito.");
		response.put("asignatura", asignaturanew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

}
