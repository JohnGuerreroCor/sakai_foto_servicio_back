package com.usco.edu.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
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
@Table(name = "uaa", schema = "dbo")
public class Uaa implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "uaa_codigo", columnDefinition = "integer")
	private Long codigo;
	
	@OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "uat_codigo")
	@NotFound(action = NotFoundAction.IGNORE)
	private UaaTipo uaaTipo;
	
	@Column(name = "uaa_nombre")
	private String nombre;

	@Column(name = "uaa_nombre_corto")
	private String nombreCorto;
	
	@Column(name = "uaa_nombre_impresion")
	private String nombreImpresion;
	
	@OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "sed_codigo")
	@NotFound(action = NotFoundAction.IGNORE)
	private Sede sede;
	
//	@OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
//	@JoinColumn(name = "mun_codigo")
//	@NotFound(action = NotFoundAction.IGNORE)
//	private Municipio municipio;
	
	@Column(name = "uaa_dependencia")
	private int uaa_dependencia;
	
	@Column(name = "uaa_telefono")
	private String telefono;
	
	@Column(name = "uaa_email")
	private String email;
	
	@Column(name = "uaa_direccion")
	private String direccion;
	
	@Column(name = "uaa_pagina")
	private String pagina;
	
	@Column(name = "uaa_jefe")
	private String jefe;
	
	@Column(name = "uaa_acronimo")
	private String acronimo;
	
	@Column(name = "uaa_centro_costos")
	private String centro_costos;

	@Column(name = "uaa_estado")
	private int estado;
	
	
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	public UaaTipo getUaaTipo() {
		return uaaTipo;
	}

	public void setUaaTipo(UaaTipo uaaTipo) {
		this.uaaTipo = uaaTipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombreCorto() {
		return nombreCorto;
	}

	public void setNombreCorto(String nombreCorto) {
		this.nombreCorto = nombreCorto;
	}

	public Sede getSede() {
		return sede;
	}

	public void setSede(Sede sede) {
		this.sede = sede;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public String getNombreImpresion() {
		return nombreImpresion;
	}

	public void setNombreImpresion(String nombreImpresion) {
		this.nombreImpresion = nombreImpresion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getPagina() {
		return pagina;
	}

	public void setPagina(String pagina) {
		this.pagina = pagina;
	}

	public String getJefe() {
		return jefe;
	}

	public void setJefe(String jefe) {
		this.jefe = jefe;
	}

	public String getAcronimo() {
		return acronimo;
	}

	public void setAcronimo(String acronimo) {
		this.acronimo = acronimo;
	}

	public String getCentro_costos() {
		return centro_costos;
	}

	public void setCentro_costos(String centro_costos) {
		this.centro_costos = centro_costos;
	}

	public int getUaa_dependencia() {
		return uaa_dependencia;
	}

	public void setUaa_dependencia(int uaa_dependencia) {
		this.uaa_dependencia = uaa_dependencia;
	}


	@Override
	public String toString() {
		return "Uaa [codigo=" + codigo + ", uaaTipo=" + uaaTipo + ", nombre=" + nombre + ", nombreCorto=" + nombreCorto
				+ ", nombreImpresion=" + nombreImpresion + ", sede=" + sede + ", uaa_dependencia=" + uaa_dependencia
				+ ", telefono=" + telefono + ", email=" + email + ", direccion=" + direccion + ", pagina=" + pagina
				+ ", jefe=" + jefe + ", acronimo=" + acronimo + ", centro_costos=" + centro_costos + ", estado="
				+ estado + "]";
	}





	private static final long serialVersionUID = 1L;

}
