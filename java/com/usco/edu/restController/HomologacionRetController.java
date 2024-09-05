package com.usco.edu.restController;

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

import com.usco.edu.entities.Homologacion;
import com.usco.edu.entities.HomologacionTipo;
import com.usco.edu.entities.SimulacionHomo;
import com.usco.edu.entities.Traslado;
import com.usco.edu.service.IHomologacionService;

@RestController
public class HomologacionRetController {
	
	@Autowired
	private IHomologacionService homoService;
	
	@GetMapping("api/simulacionhomo/{username}/{estudiante}/{planold}/{plannew}")
	public List<SimulacionHomo> asignaturasbyName( @PathVariable("username") String username, @PathVariable("estudiante") String estudiante
			, @PathVariable("planold") int planold, @PathVariable("plannew") int plannew) {
		
		List<SimulacionHomo> homo = homoService.SimularHomologaciones(username,estudiante, planold, plannew);
		return  homo;
	}
	
	@GetMapping("api/simulaciontraslado/{username}/{estudiante}/{planold}/{plannew}")
	public List<SimulacionHomo> similacionTraslado( @PathVariable("username") String username, @PathVariable("estudiante") String estudiante
			, @PathVariable("planold") int planold, @PathVariable("plannew") int plannew) {
		
		return  homoService.SimularTrasladoAsignaturas(username, estudiante, planold, plannew);
	}
	
	@GetMapping("api/{username}/homologacionesTipo")
	public List<HomologacionTipo> homolgacionesTipo( @PathVariable("username") String username) {
		List<HomologacionTipo> homo = homoService.Homotipoall(username);
		return  homo;
	}
	
	@PostMapping("/api/{username}/{estudiante}/realizarHomologacion")
	public ResponseEntity<?> create(@Valid @RequestBody Homologacion homo, BindingResult result,
			@PathVariable("username") String username,@PathVariable("estudiante") String estudiante) {
		
		Map<String, Object> response = new HashMap<>();
		System.out.println("homologacion es :"+homo);
		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {

			if(homoService.realizarHomologaciones(username, estudiante, homo)){
				response.put("mensaje", "Las Homolohaciones se realizaron con éxito.");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
				
			}else {
				response.put("mensaje", "Error al realizar el insert en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
			}
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
	
	@PostMapping("/api/{username}/{estudiante}/realizarTraslados")
	public ResponseEntity<?> realizarTraslados(@Valid @RequestBody Homologacion homo, BindingResult result,
			@PathVariable("username") String username,@PathVariable("estudiante") String estudiante) {
		
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

			if(homoService.realizarTraslados(username, estudiante, homo)){
				response.put("mensaje", "Las Homolohaciones se realizaron con éxito.");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
				
			}else {
				response.put("mensaje", "Error al realizar el insert en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
			}
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
	@GetMapping("api/homologaciones/{username}/{planold}/{plannew}")
	public List<SimulacionHomo> homologaciones( @PathVariable("username") String username
			, @PathVariable("planold") int planold, @PathVariable("plannew") int plannew) {
			Homologacion homologacion = new Homologacion();
			homologacion.setPlanold(planold);
			homologacion.setPlannew(plannew);
		List<SimulacionHomo> homo = homoService.SimularHomologacionesSinEst_codigo(username, homologacion);
		return  homo;
	}
	
	@PostMapping("/api/{username}/crearHomologacionConfig")
	public ResponseEntity<?> createHomologacionConfig(@Valid @RequestBody Homologacion homo, BindingResult result,
			@PathVariable("username") String username) {
		
		Map<String, Object> response = new HashMap<>();
		System.out.println("homologacion es :"+homo);
		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {

			if(homoService.crearHomologacion(username, homo)){
				response.put("mensaje", "La configuracion para homologar se realizo con éxito.");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
				
			}else {
				response.put("mensaje", "Error al realizar el insert en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
			}
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
	@PutMapping("/api/{username}/borrarHomologacionConfig")
	public ResponseEntity<?> borrarHomologacionConfig(@Valid @RequestBody Homologacion homo, BindingResult result, 
			@PathVariable("username") String username) {
		
		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("error", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (!homoService.validarHomologacion(username, homo)) {
			response.put("mensaje", "Error: no se pudo borrar esta configuracion de homologación ya que no existe.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
			
		try {

			if(homoService.borrarHomologacion(username, homo)){
				response.put("mensaje", "La configuracion para homologar se borro con éxito.");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
				
			}else {
				response.put("mensaje", "Error al realizar el insert en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
			}
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el plan_academico en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	
	@GetMapping("api/asignaturasparatraslado/{username}/{planold}/{plannew}")
	public List<Traslado> asignaturasfortraslado( @PathVariable("username") String username
			, @PathVariable("planold") int planold, @PathVariable("plannew") int plannew) {
			Homologacion homologacion = new Homologacion();
			homologacion.setPlanold(planold);
			homologacion.setPlannew(plannew);
		List<Traslado> homo = homoService.AsignaturasforTraslado(username, homologacion);
		return  homo;
	}
	
	
	@PostMapping("/api/{username}/crearTrasladoConfig")
	public ResponseEntity<?> createTrasladosConfig(@Valid @RequestBody Homologacion homo, BindingResult result,
			@PathVariable("username") String username) {
		
		Map<String, Object> response = new HashMap<>();
		System.out.println("homologacion es :"+homo);
		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {

			if(homoService.crearTraslado(username, homo)){
				response.put("mensaje", "La configuracion para traladar se realizo con éxito.");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
				
			}else {
				response.put("mensaje", "Error al realizar el insert en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
			}
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	

}
