package com.usco.edu.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sede", schema = "dbo")
public class Sede implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sed_codigo", columnDefinition = "integer")
	private Long codigo;

	@Column(name = "sed_nombre")
	private String nombre;

	@Column(name = "SedNombre_corto", columnDefinition = "nchar")
	private String nombreCorto;

	@Column(name = "sed_estado", columnDefinition = "nchar")
	private String estado;
	
	public Sede() {

	}

	public Sede(Long codigo, String nombre, String nombreCorto, String estado) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.nombreCorto = nombreCorto;
		this.estado = estado;
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

	public String getNombreCorto() {
		return nombreCorto;
	}

	public void setNombreCorto(String nombreCorto) {
		this.nombreCorto = nombreCorto;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Sede [codigo=" + codigo + ", nombre=" + nombre + ", nombreCorto=" + nombreCorto + ", estado=" + estado
				+ "]";
	}

	private static final long serialVersionUID = 1L;
	
}
