package com.usco.edu.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "resolucion", schema = "dbo")
public class Resolucion implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "res_codigo", columnDefinition = "integer")
	private Long codigo;
	
	@Column(name = "res_descripcion")
	private String descripcion;
	
	@Column(name = "res_numero")
	private String numero;
	
	@Column(name = "res_dependencia")
	private int dependencia;
	
	@Column(name = "res_fecha")
	private Timestamp fecha;
	
	@Column(name = "res_estado")
	private String estado;
	
	@Column(name = "for_codigo")
	private int for_codigo;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tin_codigo")
	@NotFound(action = NotFoundAction.IGNORE)
	private  NormativaTipo normativaTipo;


	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public int getDependencia() {
		return dependencia;
	}

	public void setDependencia(int dependencia) {
		this.dependencia = dependencia;
	}

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getFor_codigo() {
		return for_codigo;
	}

	public void setFor_codigo(int for_codigo) {
		this.for_codigo = for_codigo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public NormativaTipo getNormativaTipo() {
		return normativaTipo;
	}

	public void setNormativaTipo(NormativaTipo normativaTipo) {
		this.normativaTipo = normativaTipo;
	}

	@Override
	public String toString() {
		return "Resolucion [codigo=" + codigo + ", descripcion=" + descripcion + ", numero=" + numero + ", dependencia="
				+ dependencia + ", fecha=" + fecha + ", estado=" + estado + ", for_codigo=" + for_codigo
				+ ", normativaTipo=" + normativaTipo + "]";
	}


	private static final long serialVersionUID = 1L;

}
