package com.usco.edu.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class CampoDetallado {

	private int codigo;

	private String nombre;

	private CampoEspecifico campoEspecifico;

	private int estado;

	private String codigoCine;

}
