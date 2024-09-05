package com.usco.edu.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipo_normativa", schema = "dbo")
public class NormativaTipo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tin_codigo", columnDefinition = "integer")
	private Long codigo;
	
	@Column(name = "tin_nombre")
	private String nombre;
	
	@Column(name = "tin_estado")
	private int estado;

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

	@Override
	public String toString() {
		return "NormativaTipo [codigo=" + codigo + ", nombre=" + nombre + ", estado=" + estado + "]";
	}
	

}
