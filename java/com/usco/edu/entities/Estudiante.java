package com.usco.edu.entities;

import java.io.Serializable;

public class Estudiante implements Serializable{
	

	private static final long serialVersionUID = 1L;
	
	private String codigo;
	private String nombre;
	private String plan;
	private int planCodigo;
	private String uaa_nombre;
	private int uaa_codigo;
	
	public Estudiante() {
		// TODO Auto-generated constructor stub
	}
	
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
	public String getPlan() {
		return plan;
	}
	public void setPlan(String plan) {
		this.plan = plan;
	}
	public int getPlanCodigo() {
		return planCodigo;
	}
	public void setPlanCodigo(int planCodigo) {
		this.planCodigo = planCodigo;
	}
	public String getUaa_nombre() {
		return uaa_nombre;
	}
	public void setUaa_nombre(String uaa_nombre) {
		this.uaa_nombre = uaa_nombre;
	}
	public int getUaa_codigo() {
		return uaa_codigo;
	}
	public void setUaa_codigo(int uaa_codigo) {
		this.uaa_codigo = uaa_codigo;
	}
	
	@Override
	public String toString() {
		return "Estudiante [codigo=" + codigo + ", nombre=" + nombre + ", plan=" + plan + ", planCodigo=" + planCodigo
				+ ", uaa_nombre=" + uaa_nombre + ", uaa_codigo=" + uaa_codigo + "]";
	}
	
	

}
