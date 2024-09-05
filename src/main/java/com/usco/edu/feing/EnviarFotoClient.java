package com.usco.edu.feing;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.usco.edu.dto.RespuestaSubirFoto;
import com.usco.edu.dto.RespuestaVerFoto;

@FeignClient(url = "${feign.foto.url}", name = "subirfoto")
public interface EnviarFotoClient {
	
	@PostMapping(value = "/api/foto/subir2", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public RespuestaSubirFoto subirFoto(@RequestPart("file") MultipartFile file,
			@RequestHeader("Authorization") String auth, @RequestParam String jsonFoto);

	@GetMapping(value = "/api/foto/buscar-foto/{foto}")
	public RespuestaVerFoto mostrarFoto(
			@PathVariable String foto,
			@RequestHeader("Authorization") String auth);
	
	@GetMapping(value = "/api/foto/obtener-foto/{foto}")
	public String mostrarFotoAntigua(
			@PathVariable String foto,
			@RequestHeader("Authorization") String auth);

}
