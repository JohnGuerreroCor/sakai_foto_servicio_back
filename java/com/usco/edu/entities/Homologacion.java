package com.usco.edu.entities;

import java.io.Serializable;

public class Homologacion  implements Serializable{
	
	private long codigo;
	private long codigoPaaold;
	private long codigoPaanew;
	private int planold;
	private int plannew;
	private String observacion;
	private HomologacionTipo homologacionTipo;
	private int grupo;
	
	
	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public long getCodigoPaaold() {
		return codigoPaaold;
	}

	public void setCodigoPaaold(long codigoPaaold) {
		this.codigoPaaold = codigoPaaold;
	}

	public long getCodigoPaanew() {
		return codigoPaanew;
	}

	public void setCodigoPaanew(long codigoPaanew) {
		this.codigoPaanew = codigoPaanew;
	}

	public int getPlanold() {
		return planold;
	}

	public void setPlanold(int planold) {
		this.planold = planold;
	}

	public int getPlannew() {
		return plannew;
	}

	public void setPlannew(int plannew) {
		this.plannew = plannew;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public HomologacionTipo getHomologacionTipo() {
		return homologacionTipo;
	}

	public void setHomologacionTipo(HomologacionTipo homologacionTipo) {
		this.homologacionTipo = homologacionTipo;
	}

	public int getGrupo() {
		return grupo;
	}

	public void setGrupo(int grupo) {
		this.grupo = grupo;
	}

	
	@Override
	public String toString() {
		return "Homologacion [codigoPaaold=" + codigoPaaold + ", codigoPaanew=" + codigoPaanew + ", planold=" + planold
				+ ", plannew=" + plannew + ", observacion=" + observacion + ", homologacionTipo=" + homologacionTipo
				+ ", grupo=" + grupo + "]";
	}


	private static final long serialVersionUID = 1L;

}
