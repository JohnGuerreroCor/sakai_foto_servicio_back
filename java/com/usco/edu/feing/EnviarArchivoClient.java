package com.usco.edu.feing;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.usco.edu.dto.RespuestaSubirArchivo;

@FeignClient(url = "${feign.url}", name = "subirarchivos")
public interface EnviarArchivoClient {

	@PostMapping(value = "/api/v2/documento/subir", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public RespuestaSubirArchivo subirArchivo(@RequestPart("file") MultipartFile file,
			@RequestHeader("Authorization") String auth, @RequestParam String jsonDocumento);

}