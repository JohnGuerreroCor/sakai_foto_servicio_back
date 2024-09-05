package com.usco.edu.dto;

import java.io.Serializable;

import com.usco.edu.entities.Modalidad;
import com.usco.edu.entities.NivelAcademico;
import com.usco.edu.entities.Programa;
import com.usco.edu.entities.Sede;
import com.usco.edu.entities.Uaa;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReportePrograma implements Serializable {

	private Programa programa;
	private Uaa uaa;
	private Sede sede;
	private Modalidad modalidad;
	private NivelAcademico nivelAcademico;
	private String uaaDependencia;
	private String estado;
	private String formacion;

	private static final long serialVersionUID = 1L;

}
