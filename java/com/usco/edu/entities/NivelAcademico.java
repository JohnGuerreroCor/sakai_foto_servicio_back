package com.usco.edu.entities;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NivelAcademico implements Serializable {

	private Long codigo;

	private NivelAcademicoTipo nivelAcademicoTipo;

	private String nombre;

	private String estado;

	private static final long serialVersionUID = 1L;

}