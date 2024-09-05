package com.usco.edu.entities;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Documento {

	private int docCodigo;
	private String docContenido;
	private Integer uapCodigo;
	private Integer tdocCodigo;
	private String docUrl;
	private String docExtension;
	private String docEstado;
	private LocalDateTime docFechaCreado;
	private String docCodRetencion;
	private String perCodigo;
	private String docIp;
	private String docCliente;
	private String docSesion;
	private String docEncriptado;
	private String docEliminado;
	private String docEnter;
	private Integer estaCodigo;
	private String docClasificacion;
	private Integer perConvocatoria;
	private String docNombreArchivo;
	private Integer modCodigo;
	private Integer docValido;
	private Integer uaaCodigo;
}
