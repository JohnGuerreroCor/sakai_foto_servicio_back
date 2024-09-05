package com.usco.edu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespuestaSubirArchivo {

	private int idDocumento;
	private boolean estado;
	private String mensaje;
}
