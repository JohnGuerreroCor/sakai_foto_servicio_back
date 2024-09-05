package com.usco.edu.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "plan_academico_asignatura", schema = "dbo")
public class Paa implements Serializable {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "paa_codigo", columnDefinition = "integer")
	private int codigo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "asi_codigo")
	@NotFound(action = NotFoundAction.IGNORE)
	private Asignatura asignatura;
	
	@Column(name = "paa_semestre")
	private int semestre; 
	
	@Column(name = "pla_codigo")
	private int plan;
	
	@Column(name = "paa_estado")
	private int estado;
	
	@Column(name = "paa_credito")
	private int creditos;
	
	@Column(name = "paa_intensidad")
	private String intensidad;
	
	@Column(name = "paa_nota_minima")
	private double notaMinima;
	
	@Column(name = "paa_programable")
	private String programable;
	
	@Column(name = "paa_multi_asignatura")
	private String multiAsignatura;
	
	@Column(name = "paa_chequeo_requisito")
	private String chequeoRequisito;
	
	@Column(name = "paa_plan_academico")
	private String planAcademico;
	
	@Column(name = "paa_semanaxsemestre")
	private String semanaxsemestre;
	
	@Column(name = "paa_h_trabajo_ind_sem")
	private String htrabajoSemestre;
	
	@Column(name = "com_codigo")
	private Componente componente;
	
	
	public Paa() {
		// TODO Auto-generated constructor stub
	}

	public Paa(int codigo, Asignatura asignatura, int semestre, int plan, int estado, int creditos, String intensidad,
			double notaMinima, String programable, String multiAsignatura, String chequeoRequisito,
			String planAcademico, String semanaxsemestre, String htrabajoSemestre, Componente componente) {
		this.codigo = codigo;
		this.asignatura = asignatura;
		this.semestre = semestre;
		this.plan = plan;
		this.estado = estado;
		this.creditos = creditos;
		this.intensidad = intensidad;
		this.notaMinima = notaMinima;
		this.programable = programable;
		this.multiAsignatura = multiAsignatura;
		this.chequeoRequisito = chequeoRequisito;
		this.planAcademico = planAcademico;
		this.semanaxsemestre = semanaxsemestre;
		this.htrabajoSemestre = htrabajoSemestre;
		this.componente = componente;
	}



	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Asignatura getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}

	public int getSemestre() {
		return semestre;
	}

	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public double getNotaMinima() {
		return notaMinima;
	}

	public void setNotaMinima(double notaMinima) {
		this.notaMinima = notaMinima;
	}

	public String getProgramable() {
		return programable;
	}

	public void setProgramable(String programable) {
		this.programable = programable;
	}

	public String getMultiAsignatura() {
		return multiAsignatura;
	}

	public void setMultiAsignatura(String multiAsignatura) {
		this.multiAsignatura = multiAsignatura;
	}

	public int getPlan() {
		return plan;
	}

	public void setPlan(int plan) {
		this.plan = plan;
	}

	public int getCreditos() {
		return creditos;
	}

	public void setCreditos(int creditos) {
		this.creditos = creditos;
	}

	public String getIntensidad() {
		return intensidad;
	}

	public void setIntensidad(String intensidad) {
		this.intensidad = intensidad;
	}

	public Componente getComponente() {
		return componente;
	}

	public void setComponente(Componente componente) {
		this.componente = componente;
	}

	public String getChequeoRequisito() {
		return chequeoRequisito;
	}

	public void setChequeoRequisito(String chequeoRequisito) {
		this.chequeoRequisito = chequeoRequisito;
	}

	public String getPlanAcademico() {
		return planAcademico;
	}

	public void setPlanAcademico(String planAcademico) {
		this.planAcademico = planAcademico;
	}

	public String getSemanaxsemestre() {
		return semanaxsemestre;
	}

	public void setSemanaxsemestre(String semanaxsemestre) {
		this.semanaxsemestre = semanaxsemestre;
	}

	public String getHtrabajoSemestre() {
		return htrabajoSemestre;
	}

	public void setHtrabajoSemestre(String htrabajoSemestre) {
		this.htrabajoSemestre = htrabajoSemestre;
	}

	@Override
	public String toString() {
		return "Paa [codigo=" + codigo + ", asignatura=" + asignatura + ", semestre=" + semestre + ", plan=" + plan
				+ ", estado=" + estado + ", creditos=" + creditos + ", intensidad=" + intensidad + ", notaMinima="
				+ notaMinima + ", programable=" + programable + ", multiAsignatura=" + multiAsignatura
				+ ", chequeoRequisito=" + chequeoRequisito + ", planAcademico=" + planAcademico + ", semanaxsemestre="
				+ semanaxsemestre + ", htrabajoSemestre=" + htrabajoSemestre + ", componente=" + componente + "]";
	}


	private static final long serialVersionUID = 1L;
	
}
