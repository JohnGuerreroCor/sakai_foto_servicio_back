package com.usco.edu.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "convenio", schema = "dbo")
public class Convenio implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "con_codigo", columnDefinition = "integer")
	private int codigo;
	
	@Column(name = "con_nombre")
	private String nombre;
	
	@Column(name = "con_estado")
	private int estado;
	
	public Convenio() {
		// TODO Auto-generated constructor stub
	}
	
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
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	@Override
	public String toString() {
		return "Convenio [codigo=" + codigo + ", nombre=" + nombre + ", estado=" + estado + "]";
	}
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}
