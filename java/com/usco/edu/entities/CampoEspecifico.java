package com.usco.edu.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CampoEspecifico {

	private int codigo;

	private String nombre;

	private CampoAmplio campoAmplio;

	private int proPropio;

	private String codigoCine;
	
	private int estado;

	@Override
	public String toString() {
		return "CampoEspecifico [codigo=" + codigo + ", nombre=" + nombre + ", campoAmplio=" + campoAmplio
				+ ", proPropio=" + proPropio + "]";
	}

}
