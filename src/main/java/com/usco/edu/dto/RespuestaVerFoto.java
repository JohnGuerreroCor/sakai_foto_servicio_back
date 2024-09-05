package com.usco.edu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespuestaVerFoto {
	
	private String base64;
	private String extension;
	private String nombreArchivo;

}
