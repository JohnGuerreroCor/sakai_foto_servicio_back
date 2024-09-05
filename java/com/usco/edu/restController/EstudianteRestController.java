package com.usco.edu.restController;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usco.edu.entities.Estudiante;
import com.usco.edu.service.IEstudianteService;

@RestController
@RequestMapping("/est")
public class EstudianteRestController {
	
	@Autowired
	IEstudianteService estudianteservice;
	
	@GetMapping("{codigo}/{username}/plandelest")
	public ResponseEntity<?>  obtenerplandelEstudiante( @PathVariable("username") String username,@PathVariable("codigo") String codigo) {
		Map<String, Object> response = new HashMap<>();
		Estudiante est = new Estudiante();
		est = estudianteservice.findbyCodigo(codigo, username);
		
		if (est == null) {
			response.put("mensaje", "El estudiante de codigo: "
					.concat(codigo+(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		response.put("mensaje", "El estudiante esta asociado al plan: " + est.getPlan());
		response.put("estudiante", est);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
