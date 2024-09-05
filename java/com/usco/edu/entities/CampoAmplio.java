package com.usco.edu.entities;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data

public class CampoAmplio implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int codigo;

	private String nombre;

	private int estado;

	private String codigoCine;

	@Override
	public String toString() {
		return "CampoAmplio [codigo=" + codigo + ", nombre=" + nombre + ", cine=" + codigoCine + "]";
	}

}
