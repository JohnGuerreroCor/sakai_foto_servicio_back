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

import com.usco.edu.entities.Programa;
import com.usco.edu.entities.Uaa;
import com.usco.edu.entities.UaaTipo;
import com.usco.edu.service.IProgramaService;
import com.usco.edu.service.IUaaService;

@RestController
public class UaaRestController {
	
	@Autowired
	private IUaaService uaaService;
	
	@Autowired
	private IProgramaService programaService;
	
	
	@GetMapping("/uaa/uuaAll/{username}")
	public List<Uaa> allUaa(@PathVariable("username") String userdb){
		return uaaService.allUaa(userdb);
	}
	
	@GetMapping("/uaa/findByTipoUaa/{username}/{uaatipo}")
	public List<Uaa> findByTipoUaa(@PathVariable("username") String userdb,@PathVariable("uaatipo") int tipoUaa){
		return uaaService.findByTipoUaa(userdb, tipoUaa);
	}
	
	@GetMapping("/uaa/findBySede/{username}/{sede}")
	public List<Uaa> findBySede(@PathVariable("username") String userdb,@PathVariable("sede") int sed_codigo){
		return uaaService.findBySede(userdb, sed_codigo);
	}
	
	
	@GetMapping("/uaa/listUnificadas/{username}")
	public List<Uaa> listUaaUnificadas(@PathVariable("username") String userdb){
		return uaaService.listUaaUnificadas(userdb);
	}
	
/*	@GetMapping("/uaa/findById/{username}/{uaacodigo}")
	public List<Uaa> findById(@PathVariable("username") String userdb,@PathVariable("uaacodigo") int UaaCodigo){
		List<Uaa> uaa = new ArrayList<Uaa>();
		uaa.add(uaaService.findById(userdb, UaaCodigo));
		return uaa;
	}
	*/
	
	@GetMapping("/uaa/findById/{username}/{uaacodigo}")
	public ResponseEntity<?>findById(@PathVariable("username") String userdb,@PathVariable("uaacodigo") int UaaCodigo) {
		
		Map<String, Object> response = new HashMap<>();
		String cod = ""+UaaCodigo;
		if(uaaService.validarIdDeLaUaa(userdb, UaaCodigo) == false) {
			response.put("mensaje", "La UAA codigo: ".concat(cod.concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		List<Uaa> uaa = new ArrayList<Uaa>();
		uaa.add(uaaService.findById(userdb, UaaCodigo));
		return new ResponseEntity<List<Uaa>>(uaa, HttpStatus.OK);
	}
	
	@GetMapping("/uaa/uaafindById/{username}/{uaacodigo}")
	public Uaa UaafindById(@PathVariable("username") String userdb,@PathVariable("uaacodigo") int UaaCodigo){
		return uaaService.findById(userdb, UaaCodigo);
	}
	
	@GetMapping("/uaa/uuaTipos/{username}")
	public List<UaaTipo> tiposDeUaa(@PathVariable("username") String userdb){
		return uaaService.uaaTipos(userdb);
	}
	
	@GetMapping("/uaa/getTotalUaa/{username}/{uaatipo}")
	public int getTotalUaabyTipo(@PathVariable("username") String userdb,@PathVariable("uaatipo") int tipoUaa){
		return uaaService.getTotalUaabyTipo(userdb, tipoUaa);
	}
	
	@GetMapping("/uaa/getTotalUaaAll/{username}")
	public int getTotalUaa(@PathVariable("username") String userdb){
		return uaaService.getTotalUaa(userdb);
	}
	
	
	@GetMapping("/uaa/findByName/{username}/{name}")
	public List<Uaa> findByName(@PathVariable("username") String userdb,@PathVariable("name") String name){
		return uaaService.findByName(userdb, name);
	}
	
	@GetMapping("/uaa/getTotalUaaByName/{username}/{name}")
	public int getTotalUaaByName(@PathVariable("username") String userdb,@PathVariable("name") String name){
		return uaaService.getTotalUaaByName(userdb, name);
	}
	
	@PostMapping("/uaa/create/{username}")
	public ResponseEntity<?> crearUaa(@Valid @RequestBody Uaa uaa,BindingResult result,
			@PathVariable("username") String username) {
		Map<String, Object> response = new HashMap<>();
		Uaa uaaNew = new Uaa();
		Programa programaNew = new Programa();
	
		
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			uaaNew = uaaService.newUaa(username, uaa);
			// si la uaatipo es del tipo programa creamos el programa.
			if( (uaa.getUaaTipo().getCodigo()).intValue() == 3) {
				//newUaaDpto(userdb, uaa);
				Programa programa = new Programa();
				programa.setSede(uaaNew.getSede());
				programa.setUaa(uaaNew);
				
				if(!uaaService.validarUaaDpto(uaaNew)) {
					uaaService.newUaaDpto(username, uaa);
				}
			
				programaNew = programaService.newPrograma(programa, username);
			}
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "La Uaa ha sido creado con éxito.");
		response.put("uaa", uaaNew);
		if(programaNew.getCodigo() != null) {
			response.put("programa", programaNew);
		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/uaa/update/{username}")
	public ResponseEntity<?> updateUaa(@Valid @RequestBody Uaa uaa,BindingResult result,
            @PathVariable("username") String username) {
	
		Uaa uaaActual = uaaService.findById(username, uaa.getCodigo().intValue());
		
		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("error", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (uaaActual == null) {
			response.put("mensaje", "Error: no se pudo editar, la Uaa ID: "
					.concat(uaa.getCodigo()+(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
			
		try {
			uaaActual.setAcronimo(uaa.getAcronimo());
			uaaActual.setCentro_costos(uaa.getCentro_costos());
			uaaActual.setDireccion(uaa.getDireccion());
			uaaActual.setEmail(uaa.getEmail());
			uaaActual.setEstado(uaa.getEstado());
			uaaActual.setJefe(uaa.getJefe());
			uaaActual.setNombre(uaa.getNombre());
			uaaActual.setNombreCorto(uaa.getNombreCorto());
			uaaActual.setPagina(uaa.getPagina());
			uaaActual.setSede(uaa.getSede());
			uaaActual.setTelefono(uaa.getTelefono());
			uaaActual.setUaa_dependencia(uaa.getUaa_dependencia());
			uaaActual.setUaaTipo(uaa.getUaaTipo());
			uaaActual.setNombreImpresion(uaa.getNombreImpresion());
			
			uaaService.updateUaa(username, uaaActual);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar la UAA en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "La UAA se ha actualizado con éxito.");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
}
