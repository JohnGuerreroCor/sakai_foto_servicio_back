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

import com.usco.edu.entities.Asignatura;
import com.usco.edu.entities.Componente;
import com.usco.edu.entities.Paa;
import com.usco.edu.entities.Plan;
import com.usco.edu.service.IAsignaturaService;
import com.usco.edu.service.IPaaService;

@RestController
public class PaaRestController {

	@Autowired
	IPaaService paaService;
	
	@Autowired
	IAsignaturaService asignaturaService;
	
	@GetMapping("paa/{codigo}/{username}")
	public Paa paafindById(@PathVariable("codigo") int codigo,@PathVariable("username") String username) {
		return paaService.findbyId(codigo,username);
	}
	
	@GetMapping("paa/plan/{codigo}/{username}")
	public List<Paa> paafindbyIdPlan(@PathVariable("codigo") int codigo, @PathVariable("username") String username) {
		return paaService.findbyIdPlan(codigo,username);
	}
	
	@GetMapping("paa/plan/asignaturas/{username}")
	public List<Asignatura> asignaturas( @PathVariable("username") String username) {
		return asignaturaService.findAll(username);
	}
	
	@GetMapping("plan/prerrequisito/asignaturas/{plan}/{username}")
	public List<Asignatura> asignaturasPrerrequisito( @PathVariable("plan") int plan,
			@PathVariable("username") String username) {
		return asignaturaService.AsignaturasPrerrequisitos(plan, username);
	}
	
	@GetMapping("paa/plan/componentes/{username}")
	public List<Componente> componentes( @PathVariable("username") String username) {
		return  paaService.allComponent(username);
	}
	
	@GetMapping("paa/prerrequisitos/{plan}/{asignatura}/{username}")
	public List<Asignatura> perrequisitosEnAsignatura(@PathVariable("plan") int pla_codigo, 
			@PathVariable("asignatura") int asi_codigo, @PathVariable("username") String username) {
		return asignaturaService.obtenerPrerrequisitos(pla_codigo, asi_codigo, username);
	}
	
	@GetMapping("paa/prerrequisitos/creditos/{plan}/{asignatura}/{username}")
	public String perrequisitosEnCreditos(@PathVariable("plan") int pla_codigo, 
			@PathVariable("asignatura") int asi_codigo, @PathVariable("username") String username) {
		return asignaturaService.obtenerPrerrequisitoCredito(pla_codigo, asi_codigo, username);
	}
	
	@GetMapping("paa/equivalencias/{plan}/{asignatura}/{username}")
	public List<Asignatura> equivalenciasPaa(@PathVariable("plan") int pla_codigo, 
			@PathVariable("asignatura") int asi_codigo, @PathVariable("username") String username) {
		return asignaturaService.obtenerEquivalencias(pla_codigo, asi_codigo, username);
	}
	
	@PostMapping("/paa/prerrequisitos/create/{asignatura}/{username}")
	public ResponseEntity<?> crearPrerrequisito(@Valid @RequestBody Paa paa,BindingResult result,
			@PathVariable("asignatura") int asignatura,@PathVariable("username") String username) {
		System.out.println("paa:: "+paa);
		System.out.println("asignatura:: "+asignatura);
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
			asignaturaService.crearPrerrequisito(paa, asignatura, username);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El Prerrequisito ha sido creado con éxito.");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	
	@PostMapping("/paa/prerrequisitosencreditos/create/{creditos}/{username}")
	public ResponseEntity<?> crearPrerrequisitoEnCreditos(@Valid @RequestBody Paa paa,BindingResult result,
			@PathVariable("creditos") int creditos,@PathVariable("username") String username) {
		System.out.println("paa:: "+paa);
		System.out.println("asignatura:: "+creditos);
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
			asignaturaService.crearPrerrequisitoCredito(paa, creditos, username);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El Prerrequisito en creditos ha sido creado con éxito.");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	
	@PostMapping("/paa/equivalencia/create/{asignatura}/{username}")
	public ResponseEntity<?> crearEquivalencia(@Valid @RequestBody Paa paa,BindingResult result,
			@PathVariable("asignatura") int asignatura,@PathVariable("username") String username) {
		
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
			asignaturaService.crearEquivalencia(paa, asignatura, username);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "La equivalencia ha sido creada con éxito.");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/paa/update/{username}")
	public ResponseEntity<?> updatePaa(@Valid @RequestBody Paa paa,BindingResult result,
            @PathVariable("username") String username) {
		
		System.out.println(paa);
		Paa paaActual = paaService.findbyId(paa.getCodigo(), username);
		
		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("error", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (paaActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el plan_academico_asignatura ID: "
					.concat(paa.getCodigo()+(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
			
		try {
			paaActual.setAsignatura(paa.getAsignatura());
			paaActual.setSemestre(paa.getSemestre());
			paaActual.setCreditos(paa.getCreditos());
			paaActual.setIntensidad(paa.getIntensidad());
			paaActual.setEstado(paa.getEstado());
			paaActual.setNotaMinima(paa.getNotaMinima());
			paaActual.setChequeoRequisito(paa.getChequeoRequisito());
			paaActual.setPlanAcademico(paa.getPlanAcademico());
			paaActual.setProgramable(paa.getProgramable());
			paaActual.setMultiAsignatura(paa.getMultiAsignatura());
			paaActual.setSemanaxsemestre(paa.getSemanaxsemestre());
			paaActual.setHtrabajoSemestre(paa.getHtrabajoSemestre());
			
			paaService.updatePaa(paaActual, username);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el plan_academico_asignatura en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El plan_academico_asignatura se ha actualizado con éxito.");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PostMapping("/paa/create/{username}")
	public ResponseEntity<?> crearPaa(@Valid @RequestBody Paa paa,BindingResult result,
			@PathVariable("username") String username) {
		
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
			paaService.crearPaa(paa, username);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El plan_academico_asignatura ha sido creada con éxito.");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PostMapping("/paa/copiarplan/{plan}/{username}")
	public ResponseEntity<?> copiarPlanAcademico(@Valid @RequestBody Plan planCopia,BindingResult result,
			@PathVariable("plan") int plan,@PathVariable("username") String username) {
		
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
			paaService.copiarPlan(plan,planCopia.getCodigo().intValue(), username);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El plan_academico_asignatura ha sido creada con éxito.");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	
	@PutMapping("/paa/eliminarprerrequisito/{prerrequisito}/{username}")
	public ResponseEntity<?> eliminarPrerrequisito(@Valid @RequestBody Paa paa,BindingResult result,
			@PathVariable("prerrequisito") int prerrequisito,@PathVariable("username") String username) {

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
			asignaturaService.eliminarPrerrequisito(paa, prerrequisito, username);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el delate en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El Prerrequisito fue borrado con éxito.");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/paa/eliminarequivalencia/{asiequivalencia}/{username}")
	public ResponseEntity<?> eliminarEquivalencia(@Valid @RequestBody Paa paa,BindingResult result,
			@PathVariable("asiequivalencia") int asiequivalencia,@PathVariable("username") String username) {

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
			String preCreditos = asignaturaService.obtenerPrerrequisitoCredito(paa.getPlan(), asiequivalencia, username);
			List<Asignatura> prerrequisitos = asignaturaService.obtenerPrerrequisitos(paa.getPlan(), asiequivalencia, username);
			
			
			asignaturaService.eliminarEquivalencia(paa, asiequivalencia, username);
			paa.setAsignatura(new Asignatura(asiequivalencia));
			if(!prerrequisitos.isEmpty()) {
				for(Asignatura asi : prerrequisitos) {
					System.out.println(asi.getCodigo());
					asignaturaService.eliminarPrerrequisito(paa, asi.getCodigo(), username);
				}
				
			}
			
			if(Integer.parseInt(preCreditos) > 0) {
				System.out.println(preCreditos);
				asignaturaService.eliminarPrerrequisitoenCredito(paa, Integer.parseInt(preCreditos), username);
			}
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el delate en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "La equivalencia fue borrada con éxito.");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	
	@PostMapping("/paa/prerrequisitodeEqui/create/{asignaturaEqui}/{asignaturaPre}/{username}")
	public ResponseEntity<?> crearPrerrequisitoEquivalencia(@Valid @RequestBody Paa paa,BindingResult result,
		@PathVariable("asignaturaEqui") int asignaturaEqui,	@PathVariable("asignaturaPre") int asignaturaPre,
			@PathVariable("username") String username) {
		System.out.println("asignatura:: "+asignaturaEqui);
		Map<String, Object> response = new HashMap<>();
		paa.setAsignatura(new Asignatura(asignaturaEqui));
		
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			asignaturaService.crearPrerrequisito(paa, asignaturaPre, username);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		response.put("mensaje", "El Prerrequisito ha sido creado con éxito.");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PostMapping("/paa/prerrequisitosencreditos/create/{creditos}/{asignaturaEqui}/{username}")
	public ResponseEntity<?> crearPrerrequisitodeEquiEnCreditos(@Valid @RequestBody Paa paa,BindingResult result,
			@PathVariable("creditos") int creditos,@PathVariable("asignaturaEqui") int asignaturaEqui,
			@PathVariable("username") String username) {
		
		System.out.println("paa:: "+paa);
		System.out.println("asignatura:: "+creditos);
		Map<String, Object> response = new HashMap<>();
		paa.setAsignatura(new Asignatura(asignaturaEqui));
		
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			asignaturaService.crearPrerrequisitoCredito(paa, creditos, username);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El Prerrequisito en creditos ha sido creado con éxito.");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/paa/eliminarprerrequisito/{asignaturaEqui}/{prerrequisito}/{username}")
	public ResponseEntity<?> eliminarPrerrequisitodeEqui(@Valid @RequestBody Paa paa,BindingResult result,
			@PathVariable("prerrequisito") int prerrequisito,@PathVariable("asignaturaEqui") int asignaturaEqui ,@PathVariable("username") String username) {

		Map<String, Object> response = new HashMap<>();
		paa.setAsignatura(new Asignatura(asignaturaEqui));
		
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			asignaturaService.eliminarPrerrequisito(paa, prerrequisito, username);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el delate en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El Prerrequisito fue borrado con éxito.");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
}
