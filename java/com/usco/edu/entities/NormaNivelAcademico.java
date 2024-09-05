package com.usco.edu.entities;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NormaNivelAcademico implements Serializable {

	private Long codigo;
	private String nombre;
	private NormaAcreditacion norma;
	private NivelAcademico nivelAcademico;
	private int estado;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
