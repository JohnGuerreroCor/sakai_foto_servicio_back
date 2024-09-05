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
import com.usco.edu.service.IInicioSesionService;
import com.usco.edu.service.IUsuarioService;


@RestController
public class TokenRestController {

	@Autowired
	IInicioSesionService inicioSesionService;

	@Autowired
	IUsuarioService usuarioservice;

	// ENDPOINT PARA OBTENER EL TOKEN
	@GetMapping("/getToken/{username}")
	public ResponseEntity<?> getToken(@PathVariable("username") String username, HttpServletRequest request) {
		Map<String, Object> response = new HashMap<>();

		// OBTENER INFORMACIÓN DEL USUARIO
		Usuario usuario = usuarioservice.buscarUsuario(username);
		String ip = request.getRemoteAddr().toString();

		// GENERAR EL TOKEN DE SESIÓN
		String respuesta = generartoken(usuario, ip);

		System.out.println("Token generado: " + respuesta);
		response.put("correo", respuesta);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	// ENDPOINT PARA VALIDAR EL TOKEN
	@GetMapping("/validarToken/{username}/{codigo}")
	public ResponseEntity<?> validarToken(@PathVariable("username") String username,
			@PathVariable("codigo") String codigo, HttpServletRequest request) {
		Map<String, Object> response = new HashMap<>();

		// OBTENER INFORMACIÓN DEL USUARIO
		Usuario usuario = usuarioservice.buscarUsuario(username);
		String ip = request.getRemoteAddr().toString();

		// VALIDAR EL CÓDIGO DE VERIFICACIÓN
		boolean respuesta = validarToken(usuario, ip, codigo);
		System.out.println("Entramos a validar el token");

		if (respuesta) {
			response.put("mensaje", "Código de verificación correcto");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} else {
			response.put("mensaje", "Código de verificación incorrecto.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
	}

	// MÉTODO PARA GENERAR EL TOKEN DE SESIÓN
	private String generartoken(Usuario usuarioLogueado, String ip) {
		String aplicativo = "1"; //IMPORTANTE REEMPLAZAR ESTE CÓDIGO CON EL MÓDULO CORRESPONDIENTE, BUSCAR EN LA TABLA MODULO DE LA BASE DE DATOS
		System.out.println("Datos para el token: " + aplicativo + usuarioLogueado.getId() + ip);

		// OBTENER EL TOKEN DE SESIÓN DESDE EL SERVICIO
		String tokenSesion = inicioSesionService
				.obtenerTokenInicioSesion(aplicativo + usuarioLogueado.getId() + ip, usuarioLogueado.getUsername());

		// CONSTRUIR LA URL PARA GENERAR EL CÓDIGO
		String url = inicioSesionService.urlTokenPeticion(usuarioLogueado.getUsername()) + "api/generarCodigo";
		System.out.println("URL para generar código: " + url);

		// CODIFICAR EL TOKEN DE SESIÓN EN BASE64
		String tokenSesionbase64 = Base64.getEncoder().encodeToString(tokenSesion.getBytes());

		// Configurar las cabeceras para la solicitud HTTP
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", tokenSesionbase64);
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		// CODIFICAR DATOS ADICIONALES EN BASE64
		String aplicativobase64 = Base64.getEncoder().encodeToString(aplicativo.getBytes());
		String usuariobase64 = Base64.getEncoder().encodeToString(String.valueOf(usuarioLogueado.getId()).getBytes());
		String ipbase64 = Base64.getEncoder().encodeToString(ip.getBytes());

		// CONSTRUIR EL CUERPO DE LA SOLICITUD HTTP
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("aplicativo", aplicativobase64);
		map.add("usuario", usuariobase64);
		map.add("ip", ipbase64);

		final HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(map,
				headers);
		String respuesta = null;

		try {
			// REALIZAR LA SOLICITUD HTTP PARA GENERAR EL CÓDIGO
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<RespuestaToken> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity,
					RespuestaToken.class);

			if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
				// OBTENER LA RESPUESTA DEL SERVICIO
				respuesta = responseEntity.getBody().getMensaje();
			}
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}

		System.out.println("Mensaje del servicio: " + respuesta);
		return respuesta;
	}

	// MÉTODO PARA VALIDAR EL CÓDIGO DE VERIFICACIÓN
	private boolean validarToken(Usuario usuarioLogueado, String ip, String CodigoVerificacion) {
		String aplicativo = "1"; //IMPORTANTE REEMPLAZAR ESTE CÓDIGO CON EL MÓDULO CORRESPONDIENTE, BUSCAR EN LA TABLA MODULO DE LA BASE DE DATOS

		// OBTENER EL TOKEN DE SESIÓN DESDE EL SERVICIO
		String tokenSesion = inicioSesionService
				.obtenerTokenInicioSesion(aplicativo + usuarioLogueado.getId() + ip, usuarioLogueado.getUsername());

		// CONSTRUIR LA URL PARA VALIDAR EL CÓDIGO
		String url = inicioSesionService.urlTokenPeticion(usuarioLogueado.getUsername()) + "api/validarCodigo";
		String tokenSesionbase64 = Base64.getEncoder().encodeToString(tokenSesion.getBytes());

		// CONFIGURAR LAS CABECERAS PARA LA SOLICITUD HTTP
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", tokenSesionbase64);
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		// CODIFICAR DATOS ADICIONALES EN BASE64
		String aplicativobase64 = Base64.getEncoder().encodeToString(aplicativo.getBytes());
		String usuariobase64 = Base64.getEncoder().encodeToString(String.valueOf(usuarioLogueado.getId()).getBytes());
		String ipbase64 = Base64.getEncoder().encodeToString(ip.getBytes());
		String CodigoVerificacionbase64 = Base64.getEncoder().encodeToString(CodigoVerificacion.getBytes());

		// CONSTRUIR EL CUERPO DE LA SOLICITUD HTTP
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("aplicativo", aplicativobase64);
		map.add("usuario", usuariobase64);
		map.add("ip", ipbase64);
		map.add("codigoVerificacion", CodigoVerificacionbase64);

		final HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(map,
				headers);
		Boolean respuesta = false;

		try {
			// REALIZAR LA SOLICITUD HTTP PARA VALIDAR EL CÓDIGO
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<RespuestaToken> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity,
					RespuestaToken.class);

			if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
				// OBTENER LA RESPUESTA DEL SERVICIO
				respuesta = responseEntity.getBody().isEstado();
				System.out.println("Respuesta al validar: " + responseEntity.getBody().getMensaje());
				System.out.println("Respuesta al validar: " + respuesta);
			}
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}

		System.out.println("Respuesta al validar: " + respuesta);
		return respuesta;
	}
}
