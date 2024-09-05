package com.usco.edu.entities;

import java.io.Serializable;

public class SimulacionHomo implements Serializable{
	
	private long codigo;
	private long codigoPaaold;
	private long codigoPaanew;
	private String asignaturaold;
	private String asignaturanew;
	private String curso;
	private double nota;
	private int hot_codigo;
	private String hot_nombre;
	private int repetir;
	private long matricula;
	private String subgrupo;
	private String uaanew;
	
	
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

	public double getNota() {
		return nota;
	}

	public void setNota(double nota) {
		this.nota = nota;
	}

	public int getHot_codigo() {
		return hot_codigo;
	}

	public void setHot_codigo(int hot_codigo) {
		this.hot_codigo = hot_codigo;
	}

	public int getRepetir() {
		return repetir;
	}

	public void setRepetir(int repetir) {
		this.repetir = repetir;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public long getMatricula() {
		return matricula;
	}

	public void setMatricula(long matricula) {
		this.matricula = matricula;
	}

	public String getSubgrupo() {
		return subgrupo;
	}

	public void setSubgrupo(String subgrupo) {
		this.subgrupo = subgrupo;
	}
	
	public String getHot_nombre() {
		return hot_nombre;
	}

	public void setHot_nombre(String hot_nombre) {
		this.hot_nombre = hot_nombre;
	}

	public String getUaanew() {
		return uaanew;
	}

	public void setUaanew(String uaanew) {
		this.uaanew = uaanew;
	}


	@Override
	public String toString() {
		return "SimulacionHomo [codigoPaaold=" + codigoPaaold + ", codigoPaanew=" + codigoPaanew + ", asignaturaold="
				+ asignaturaold + ", asignaturanew=" + asignaturanew + ", curso=" + curso + ", nota=" + nota
				+ ", hot_codigo=" + hot_codigo + ", hot_nombre=" + hot_nombre + ", repetir=" + repetir + ", matricula="
				+ matricula + ", subgrupo=" + subgrupo + ", uaanew=" + uaanew + "]";
	}




	private static final long serialVersionUID = 1L;

}
