package com.usco.edu.entities;

import java.io.Serializable;

public class Traslado  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoPaaold;
	private String codigoPaanew;
	private String asignaturaold;
	private String asignaturanew;
	private String hot_nombre;
	private String aplica;
	private String planold;
	private String plannew;
	private String semestreasiold;
	private String semestreasinew;
	private int grupo;
	private int hot_codigo;
	
	public String getCodigoPaaold() {
		return codigoPaaold;
	}
	public void setCodigoPaaold(String codigoPaaold) {
		this.codigoPaaold = codigoPaaold;
	}
	public String getCodigoPaanew() {
		return codigoPaanew;
	}
	public void setCodigoPaanew(String codigoPaanew) {
		this.codigoPaanew = codigoPaanew;
	}
	public String getAsignaturaold() {
		return asignaturaold;
	}
	public void setAsignaturaold(String asignaturaold) {
		this.asignaturaold = asignaturaold;
	}
	public String getAsignaturanew() {
		return asignaturanew;
	}
	public void setAsignaturanew(String asignaturanew) {
		this.asignaturanew = asignaturanew;
	}
	public String getHot_nombre() {
		return hot_nombre;
	}
	public void setHot_nombre(String hot_nombre) {
		this.hot_nombre = hot_nombre;
	}
	public String getAplica() {
		return aplica;
	}
	public void setAplica(String aplica) {
		this.aplica = aplica;
	}
	public String getPlanold() {
		return planold;
	}
	public void setPlanold(String planold) {
		this.planold = planold;
	}
	public String getPlannew() {
		return plannew;
	}
	public void setPlannew(String plannew) {
		this.plannew = plannew;
	}
	
	public String getSemestreasiold() {
		return semestreasiold;
	}
	public void setSemestreasiold(String semestreasiold) {
		this.semestreasiold = semestreasiold;
	}
	public String getSemestreasinew() {
		return semestreasinew;
	}
	public void setSemestreasinew(String semestreasinew) {
		this.semestreasinew = semestreasinew;
	}
	
	public int getGrupo() {
		return grupo;
	}
	public void setGrupo(int grupo) {
		this.grupo = grupo;
	}
	public int getHot_codigo() {
		return hot_codigo;
	}
	public void setHot_codigo(int hot_codigo) {
		this.hot_codigo = hot_codigo;
	}
	@Override
	public String toString() {
		return "Traslado [codigoPaaold=" + codigoPaaold + ", codigoPaanew=" + codigoPaanew + ", asignaturaold="
				+ asignaturaold + ", asignaturanew=" + asignaturanew + ", hot_nombre=" + hot_nombre + ", aplica="
				+ aplica + ", planold=" + planold + ", plannew=" + plannew + ", semestreasiold=" + semestreasiold
				+ ", semestreasinew=" + semestreasinew + "]";
	}
	


}
