package com.usco.edu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespuestaSubirFoto {
	
	private String perCodigo;
	private boolean estado;
	private String mensaje;

}
