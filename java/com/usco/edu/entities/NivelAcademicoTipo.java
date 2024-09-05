package com.usco.edu.entities;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NivelAcademicoTipo implements Serializable {


	private Long codigo;


	private String nombre;


	private static final long serialVersionUID = 1L;

}