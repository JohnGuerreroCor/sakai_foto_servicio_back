package com.usco.edu.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "uaa_tipo", schema = "dbo")
public class UaaTipo implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "uat_codigo", columnDefinition = "integer")
	private Long codigo;

	@Column(name = "uat_nombre")
	private String nombre;
	
	//@Column(name = "uat_estado")
	//private int estado;
	
	public UaaTipo() {
		// TODO Auto-generated constructor stub
	}

	public UaaTipo(Long codigo, String nombre) {
		this.codigo = codigo;
		this.nombre = nombre;
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

	//public int getEstado() {
	//	return estado;
	/*}

	public void setEstado(int estado) {
		this.estado = estado;
	}*/

	@Override
	public String toString() {
		return "UaaTipo [codigo=" + codigo + ", nombre=" + nombre + "]";
	}


	private static final long serialVersionUID = 1L;
}
