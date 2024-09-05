package com.usco.edu.entities;

import java.io.Serializable;
import java.sql.Timestamp;


public class Plan implements Serializable {
	
	
	private Long codigo;

	private String pla_nombre;
	

	private  Programa programa;
	
	
	private  Resolucion resolucion;
	

	private int pla_creditos;
	
	
	private int creditosTotal;
	
	
	private String tipoRegistro;
	
	
	private int estado ;
	
	
	private int plan_actual ;
	
	
	private Timestamp fechaCreacion;
	
	public Plan() {
		// TODO Auto-generated constructor stub
	}
	

	public Plan(Long codigo, String pla_nombre, Programa programa, Resolucion resolucion, int pla_creditos,
			int creditosTotal, String tipoRegistro, int estado, int plan_actual, Timestamp fechaCreacion) {
		this.codigo = codigo;
		this.pla_nombre = pla_nombre;
		this.programa = programa;
		this.resolucion = resolucion;
		this.pla_creditos = pla_creditos;
		this.creditosTotal = creditosTotal;
		this.tipoRegistro = tipoRegistro;
		this.estado = estado;
		this.plan_actual = plan_actual;
		this.fechaCreacion = fechaCreacion;
	}



	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getPla_nombre() {
		return pla_nombre;
	}

	public void setPla_nombre(String pla_nombre) {
		this.pla_nombre = pla_nombre;
	}

	public Programa getPrograma() {
		return programa;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
	}

	public int getPla_creditos() {
		return pla_creditos;
	}

	public void setPla_creditos(int pla_creditos) {
		this.pla_creditos = pla_creditos;
	}

	public int getCreditosTotal() {
		return creditosTotal;
	}

	public void setCreditosTotal(int creditosTotal) {
		this.creditosTotal = creditosTotal;
	}

	public String getTipoRegistro() {
		return tipoRegistro;
	}

	public void setTipoRegistro(String tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Timestamp getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public int getPlan_actual() {
		return plan_actual;
	}

	public void setPlan_actual(int plan_actual) {
		this.plan_actual = plan_actual;
	}

	public Resolucion getResolucion() {
		return resolucion;
	}

	public void setResolucion(Resolucion resolucion) {
		this.resolucion = resolucion;
	}


	@Override
	public String toString() {
		return "Plan [codigo=" + codigo + ", pla_nombre=" + pla_nombre + ", programa=" + programa + ", resolucion="
				+ resolucion + ", pla_creditos=" + pla_creditos + ", creditosTotal=" + creditosTotal + ", tipoRegistro="
				+ tipoRegistro + ", estado=" + estado + ", plan_actual=" + plan_actual + ", fechaCreacion="
				+ fechaCreacion + "]";
	}



	private static final long serialVersionUID = 1L;

}
