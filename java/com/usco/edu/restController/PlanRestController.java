package com.usco.edu.restController;

import java.sql.Timestamp;
import java.util.Date;
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.usco.edu.entities.Plan;
import com.usco.edu.service.IPlanService;

@CrossOrigin(origins =  "*" , methods= {RequestMethod.GET,RequestMethod.POST})
@RestController
public class PlanRestController {


	@Autowired
	IPlanService planService;
	
	
	@GetMapping("plan/programa/{codigo}/{username}")
	public List<Plan> planfindByprograma(@PathVariable("codigo") int codigo,@PathVariable("username") String username) {
		return planService.planesPorPrograma(codigo,username);
	}
	
	@GetMapping("plan/uaa/{codigo}/{username}")
	public List<Plan> planfindByUaa(@PathVariable("codigo") int codigo,@PathVariable("username") String username) {
		return planService.planesPorUaa(codigo, username);
	}
	
	@GetMapping("/api/plan/{codigo}/{username}")
	public List<Plan> findbyIdList(@PathVariable("codigo") int codigo,@PathVariable("username") String username) {
		return planService.findByCodigoList(codigo,username);
	}
	
	@GetMapping("/api/planesparatraslados/{codigo}/{username}")
	public List<Plan> findbyIdTraslados (@PathVariable("codigo") int codigo,@PathVariable("username") String username) {
		return planService.findByCodigotraslados(username, codigo);
	}
	
	@GetMapping("/api/planbynombre/{nombre}/{username}")
	public List<Plan> findbyNombrePlan(@PathVariable("nombre") String nombre,@PathVariable("username") String username) {
		return planService.findByNombre(nombre, username);
	}
	
	@GetMapping("/api/{codigo}/{username}")
	public Plan findbyId(@PathVariable("codigo") int codigo,@PathVariable("username") String username) {
		return planService.findByCodigo(codigo,username);
	}
	
	@PutMapping("/api/plan/update/{id}/{username}")
	public ResponseEntity<?> update(@Valid @RequestBody Plan plan, BindingResult result,
			@PathVariable int id, @PathVariable("username") String username) {

		Plan planActual = planService.findByCodigo(id,username);
		
		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("error", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (planActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el plan_academico ID: "
					.concat(id+(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
			
		try {
			planActual.setPla_nombre(plan.getPla_nombre());
			planActual.setCreditosTotal(plan.getCreditosTotal());
			planActual.setEstado(plan.getEstado());
			planActual.setPla_creditos(plan.getPla_creditos());
			planActual.setPlan_actual(plan.getPlan_actual());
			planActual.setPrograma(plan.getPrograma());			
			planActual.setResolucion(plan.getResolucion());
			planActual.setTipoRegistro(plan.getTipoRegistro());
			
			planService.updatePlan(planActual,username);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el plan_academico en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El plan academico se ha actualizado con éxito.");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	
	@PostMapping("/api/plan/create/{username}")
	public ResponseEntity<?> create(@Valid @RequestBody Plan plan, BindingResult result,
			@PathVariable("username") String username) {
		
		Plan planNew = new Plan();
		Map<String, Object> response = new HashMap<>();
		System.out.println("plan res :"+plan);
		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			Date fechaCreacion = new Date();  
			 
	        Timestamp ts =new Timestamp(fechaCreacion.getTime()); 
	        
			plan.setFechaCreacion(ts);
			planNew = planService.createPlan(plan,username);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El Plan academico ha sido creado con éxito.");
		response.put("plan", planNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
}
