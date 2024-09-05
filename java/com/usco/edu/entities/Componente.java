package com.usco.edu.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "componente", schema = "dbo")
public class Componente implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "com_codigo", columnDefinition = "integer")
	private int codigo;
	
	@Column(name = "com_nombre")
	private String nombre;
	
	@Column(name = "com_acronimo")
	private String acronimo;
	
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

	public String getAcronimo() {
		return acronimo;
	}

	public void setAcronimo(String acronimo) {
		this.acronimo = acronimo;
	}

	@Override
	public String toString() {
		return "Componente [codigo=" + codigo + ", nombre=" + nombre + ", acronimo=" + acronimo + "]";
	}



	private static final long serialVersionUID = 1L;

}
