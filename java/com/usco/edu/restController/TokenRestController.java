package com.usco.edu.restController;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.usco.edu.entities.RespuestaToken;
import com.usco.edu.entities.Usuario;
import com.usco.edu.service.IAdministradorService;
import com.usco.edu.service.IUsuarioService;


@RestController
public class TokenRestController {
	
	@Autowired
	IAdministradorService administrativoService;
	
	@Autowired 
	IUsuarioService usuarioservice;
	
	@GetMapping("/getToken/{username}")
	public ResponseEntity<?>  getToken(@PathVariable("username") String username ,HttpServletRequest request) {
		Map<String, Object> response = new HashMap<>();
		Usuario usuario = usuarioservice.findByUsername(username);
		String ip = request.getRemoteAddr().toString();
		String respuesta = generartoken(usuario,ip);
		
		System.out.println("token :"+respuesta);
		response.put("correo", respuesta);
		return new ResponseEntity<Map<String, Object>>(response,  HttpStatus.OK);
	}
	
	@GetMapping("/validarToken/{username}/{codigo}")
	public ResponseEntity<?> validarToken(@PathVariable("username") String username ,@PathVariable("codigo") String codigo ,HttpServletRequest request) {
		Map<String, Object> response = new HashMap<>();
		Usuario usuario = usuarioservice.findByUsername(username);
		String ip = request.getRemoteAddr().toString();
		boolean respuesta = validarToken(usuario, ip, codigo);
		System.out.println("Entramos validar token");
		
		if(respuesta) {
			response.put("mensaje", "Código de verificación correcto");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		}else {
			response.put("mensaje", "Código de verificación  incorrecto.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		

	}
	
	private String generartoken(Usuario usuarioLogueado , String ip ) {
		//MODULO SOLICITUD ACCESO TOKEN
		String aplicativo = "17";
		System.out.println(aplicativo+""+usuarioLogueado.getId() + ip);
		String tokenSesion = administrativoService.getTokenInicioSesion(aplicativo+""+usuarioLogueado.getId() + ip , usuarioLogueado.getUsername());		
		String url  = administrativoService.urltokenPeticion(usuarioLogueado.getUsername()) + "api/generarCodigo";
		System.out.println(url);
		String tokenSesionbase64 = Base64.getEncoder().encodeToString(tokenSesion.getBytes());
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", tokenSesionbase64);
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED); 
		
		
		String aplicativobase64 = Base64.getEncoder().encodeToString(aplicativo.getBytes());
		String usuariobase64 = Base64.getEncoder().encodeToString((""+usuarioLogueado.getId()).getBytes() );
		String ipbase64  = Base64.getEncoder().encodeToString((ip).getBytes());
		
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();		
		map.add("aplicativo", aplicativobase64);
		map.add("usuario", usuariobase64);
		map.add("ip", ipbase64);
		
		final HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(map ,
		        headers);
		String respuesta = null;
		try {
		    RestTemplate restTemplate = new RestTemplate();
		    ResponseEntity<RespuestaToken> responseEntity = restTemplate.exchange(
		            url, HttpMethod.POST, entity,RespuestaToken.class);
		    
		    if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
		        try {
		        	respuesta = responseEntity.getBody().getMensaje();
		        } catch (Exception e) {
		            throw new RuntimeException("JSONException occurred");
		        }
		    }
		} catch (Exception exception) {
			 throw new RuntimeException(exception);
	    } 
		System.out.println("mensaje1: "+respuesta);
		return respuesta;
	}
	
	private boolean validarToken(Usuario usuarioLogueado , String ip , String CodigoVerificacion ) {
		//MODULO SOLICITUD ACCESO TOKEN
		String aplicativo = "17";
		String tokenSesion = administrativoService.getTokenInicioSesion(aplicativo+""+usuarioLogueado.getId() + ip , usuarioLogueado.getUsername());	
		String url  = administrativoService.urltokenPeticion(usuarioLogueado.getUsername()) + "api/validarCodigo";
		String tokenSesionbase64 = Base64.getEncoder().encodeToString(tokenSesion.getBytes());
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", tokenSesionbase64);
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED); 

		String aplicativobase64 = Base64.getEncoder().encodeToString((aplicativo).getBytes());
		String usuariobase64 = Base64.getEncoder().encodeToString((""+usuarioLogueado.getId()).getBytes() );
		String ipbase64  = Base64.getEncoder().encodeToString((ip).getBytes());
		String CodigoVerificacionbase64 = Base64.getEncoder().encodeToString((CodigoVerificacion).getBytes());
		
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();		
		map.add("aplicativo", aplicativobase64);
		map.add("usuario", usuariobase64);
		map.add("ip", ipbase64);
		map.add("codigoVerificacion", CodigoVerificacionbase64);
		
		
		final HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(map ,
		        headers);
		Boolean respuesta = false;
		try {
		    RestTemplate restTemplate = new RestTemplate();
		    ResponseEntity<RespuestaToken> responseEntity = restTemplate.exchange(
		            url, HttpMethod.POST, entity,RespuestaToken.class);
		    if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
		        try {
		        	respuesta = responseEntity.getBody().isEstado();
		        	System.out.println("repsuesta al validar a: "+responseEntity.getBody().getMensaje());
		        	System.out.println("repsuesta al validar 0 : "+respuesta);
		        } catch (Exception e) {
		            throw new RuntimeException("JSONException occurred");
		        }
		    }
		}catch (Exception exception) {
				 throw new RuntimeException(exception);
		    } 
		
		System.out.println("repsuesta al validar : "+respuesta);
		return respuesta;
	}

}
