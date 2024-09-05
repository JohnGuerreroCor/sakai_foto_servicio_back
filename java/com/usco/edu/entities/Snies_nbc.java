package com.usco.edu.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "snies_nbc", schema = "dbo")
public class Snies_nbc implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "nbc_codigo", columnDefinition = "integer")
	private int codigo;
	
	@Column(name = "nbc_nombre")
	private String nombre;
	
	@Column(name = "sar_codigo")
	private int snies_area;
	

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getSnies_area() {
		return snies_area;
	}

	public void setSnies_area(int snies_area) {
		this.snies_area = snies_area;
	}


	@Override
	public String toString() {
		return "Snies_nbc [codigo=" + codigo + ", nombre=" + nombre + ", snies_area=" + snies_area + "]";
	}


	private static final long serialVersionUID = 1L;
	
}
