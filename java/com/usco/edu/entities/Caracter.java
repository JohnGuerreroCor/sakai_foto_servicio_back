package com.usco.edu.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "caracter", schema = "dbo")
public class Caracter implements Serializable{

	@Id
	@Column(name = "car_codigo")
	private String codigo;
	
	@Column(name = "car_nombre")
	private String nombre;
	
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	

	private static final long serialVersionUID = 1L;
	

}
