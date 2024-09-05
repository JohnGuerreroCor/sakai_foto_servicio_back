package com.usco.edu.entities;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NormaAcreditacion implements Serializable {

	private int codigo;

	private String nombre;

	private Date fin;

	private Date inicio;

	private int estado;

	private String pdf;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
