package com.usco.edu.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "nucleo", schema = "dbo")
public class Nucleo implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "nuc_codigo", columnDefinition = "integer")
	private Long codigo;
	
	@Column(name = "nuc_nombre")
	private String nombre;
	
	@Column(name = "nuc_estado")
	private int estado;
	
	@Column(name = "nuc_acronimo")
	private String acronimo;

	
	public Nucleo(Long codigo, String nombre, int estado, String acronimo) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.estado = estado;
		this.acronimo = acronimo;
	}
	
	public Nucleo() {
		// TODO Auto-generated constructor stub
	}


	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public String getAcronimo() {
		return acronimo;
	}

	public void setAcronimo(String acronimo) {
		this.acronimo = acronimo;
	}


	@Override
	public String toString() {
		return "Nucleo [codigo=" + codigo + ", nombre=" + nombre + ", estado=" + estado + ", acronimo=" + acronimo
				+ "]";
	}

	private static final long serialVersionUID = 1L;
}
